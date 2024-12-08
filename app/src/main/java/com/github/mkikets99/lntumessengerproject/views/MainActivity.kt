package com.github.mkikets99.lntumessengerproject.views

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import com.github.mkikets99.lntumessengerproject.controllers.IntNavController
import com.github.mkikets99.lntumessengerproject.services.FirebaseService

class MainActivity : AppCompatActivity() {

    private val googleAct = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            //switchToChats()
        } else {
            Log.e("FirebaseUI", "Sign-in failed")
        }
    }

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WindowInsets.safeDrawing
            IntNavController()
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
//        switchToChats()
//        updateUI(currentUser)


    }
//    private fun switchToChats(){
//        if( FirebaseService.instance.collectAuthData().currentUser != null) {
//            var user = User(naming.text.toString(),FirebaseAuth.getInstance().uid!!, ArrayList())
//
//            FirebaseService.instance.requestData("users") { it, ex ->
//                if (ex != null) {
//                    Log.e("MainActivity", ex.message.toString())
//                    finish()
//                }
//                for (docs in it!!.documents) {
//                    if (docs.contains("uuid") && docs["uuid"]!! == FirebaseAuth.getInstance().uid!!) {
//                        user = docs.toObject(User::class.java)!!
//                        user._key = docs.id
//                        FirebaseService.instance.updateData(
//                            "users", docs.id, user
//                        ){ v,e->
//                            if(e!=null){
//                                Log.e("MainActivity",e.message.toString())
//                                finish()
//                            }
//                        }
//                    }
//                }
//                if (user._key == null) {
//                    FirebaseService.instance.appendData("users",user) { dr,e ->
//                        if(e!=null){
//                            Log.e("MainActivity",e.message.toString())
//                            finish()
//                        }
//
//                        user._key = dr!!.id
//                    }
//                }
//                with(sharedPref.edit()) {
//                    putString("username", naming.text.toString())
//                    putString("user_key", user._key)
//                    apply()
//                }
//
//                val intent = Intent(this, MainListActivity::class.java)
//                startActivity(intent)
//                finish()
//            }
//
//        }
//    }
    private fun signInWithGoogle() {
        FirebaseService.instance.requestAuth(googleAct)
    }


}
