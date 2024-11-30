package com.github.mkikets99.lntumessengerproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.github.mkikets99.lntumessengerproject.databinding.AuthorizationLayoutBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: AuthorizationLayoutBinding
    private lateinit var auth: FirebaseAuth

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
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

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
            val intent = Intent(this, MainListActivity::class.java)
            startActivity(intent)
            finish()
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
