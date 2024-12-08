package com.github.mkikets99.lntumessengerproject.views

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.github.mkikets99.lntumessengerproject.classes.User
import com.github.mkikets99.lntumessengerproject.databinding.AuthorizationLayoutBinding
import com.github.mkikets99.lntumessengerproject.services.FirebaseService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: AuthorizationLayoutBinding
    private lateinit var auth: FirebaseAuth

    private lateinit var naming: EditText

    private lateinit var sharedPref: SharedPreferences

    private val database: FirebaseFirestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = AuthorizationLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseService.instance.init(this)
        sharedPref = this.getSharedPreferences("settings", Context.MODE_PRIVATE)
        // Initialize Firebase Auth
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
            var user = User(naming.text.toString(),FirebaseAuth.getInstance().uid!!, ArrayList())

            FirebaseService.instance.requestData("users") { it, ex ->
                if (ex != null) {
                    Log.e("MainActivity", ex.message.toString())
                    finish()
                }
                for (docs in it!!.documents) {
                    if (docs.contains("uuid") && docs["uuid"]!! == FirebaseAuth.getInstance().uid!!) {
                        user = docs.toObject(User::class.java)!!
                        user._key = docs.id
                        database.collection("users").document(docs.id).set(user)
                    }
                }
                if (user._key == null) {
                    database.collection("users").add(user).addOnSuccessListener {
                        user._key = it.id
                    }
                }
                with(sharedPref.edit()) {
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
        FirebaseService.instance.requestAuth(registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                switchToChats()
            } else {
                Log.e("FirebaseUI", "Sign-in failed")
            }
        })
    }


}
