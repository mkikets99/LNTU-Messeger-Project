package com.github.mkikets99.lntumessengerproject.views

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
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: AuthorizationLayoutBinding

    private lateinit var naming: EditText

    private val googleAct = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            switchToChats()
        } else {
            Log.e("FirebaseUI", "Sign-in failed")
        }
    }

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = AuthorizationLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseService.instance.init(this)
        sharedPref = this.getSharedPreferences("settings", MODE_PRIVATE)
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
//        switchToChats()
//        updateUI(currentUser)


    }
    private fun switchToChats(){
        if( FirebaseService.instance.collectAuthData().currentUser != null) {
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
                        FirebaseService.instance.updateData(
                            "users", docs.id, user
                        ){ v,e->
                            if(e!=null){
                                Log.e("MainActivity",e.message.toString())
                                finish()
                            }
                        }
                    }
                }
                if (user._key == null) {
                    FirebaseService.instance.appendData("users",user) { dr,e ->
                        if(e!=null){
                            Log.e("MainActivity",e.message.toString())
                            finish()
                        }

                        user._key = dr!!.id
                    }
                }
                with(sharedPref.edit()) {
                    putString("username", naming.text.toString())
                    putString("user_key", user._key)
                    apply()
                }

                val intent = Intent(this, MainListActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }
    private fun signInWithGoogle() {
        FirebaseService.instance.requestAuth(googleAct)
    }


}
