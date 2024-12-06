package com.github.mkikets99.lntumessengerproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.github.mkikets99.lntumessengerproject.classes.User
import com.github.mkikets99.lntumessengerproject.databinding.AuthorizationLayoutBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: AuthorizationLayoutBinding
    private lateinit var auth: FirebaseAuth

    private lateinit var naming: EditText

    private lateinit var sharedPref: SharedPreferences

    private val database: FirebaseFirestore = Firebase.firestore

    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
            ) { result ->
        if (result.resultCode == RESULT_OK) {
            switchToChats()

//            Toast.makeText(this, "Welcome ${user?.displayName}", Toast.LENGTH_SHORT).show()
        } else {
            Log.e("FirebaseUI", "Sign-in failed")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = AuthorizationLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        sharedPref = this.getSharedPreferences("settings", Context.MODE_PRIVATE)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        naming = binding.nameField
        if(sharedPref.contains("username")){
            naming.text.append(sharedPref.getString("username",""))
        }

        // Set the Google sign-in button click listener
        binding.googleButton.setOnClickListener {
                signInWithGoogle()

                Toast.makeText(this,"Test",Toast.LENGTH_LONG).show()
            }
//        setContent {
//            LNTUMessengerProjectTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
////                    Greeting(
////                        name = "Android",
////                        modifier = Modifier.padding(innerPadding)
////                    )
//                    TestMessenger(Modifier.padding(innerPadding))
//                }
//            }
//        }
//        test(param1 = "MyName") {
//            Log.d("MyTag","")
//        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        switchToChats()
//        updateUI(currentUser)


    }
    private fun switchToChats(){
        if( auth.currentUser != null) {
            val user = User(naming.text.toString(),FirebaseAuth.getInstance().uid!!, ArrayList())

            database.collection("users").get().addOnSuccessListener {
                for (docs in it.documents) {
                    if(docs.contains("uuid")&& docs["uuid"]!! == FirebaseAuth.getInstance().uid!!){
                        database.collection("users").document(docs.id).set(user)
                        user._key = docs.id
                    }
                }
                if (user._key == null){
                    database.collection("users").add(user).addOnSuccessListener {
                        user._key = it.id
                    }
                }
                with (sharedPref.edit()) {
                    putString("username", naming.text.toString())
                    apply()
                }

                val intent = Intent(this, MainListActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }
    private fun signInWithGoogle() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        googleSignInLauncher.launch(signInIntent)
    }


}
