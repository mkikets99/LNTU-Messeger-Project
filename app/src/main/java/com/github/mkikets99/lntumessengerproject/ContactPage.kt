package com.github.mkikets99.lntumessengerproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.mkikets99.lntumessengerproject.classes.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class ContactPage : AppCompatActivity() {

    private lateinit var naming : TextView
    private var name : String? = null
    private var currentUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContentView(R.layout.activity_contact_page)
        fetchName()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Firebase.firestore.collection("users")
            .get().addOnSuccessListener {
                for(docs in it.documents){
                    if(docs.get("uuid") == FirebaseAuth.getInstance().uid){
                        currentUser = docs.toObject(User::class.java)
                        currentUser?._key = docs.id
                        break
                    }
                }

            }

        naming = findViewById<TextView>(R.id.naming)
        naming.text = name
        // Fetch data from Firebase
    }

    @SuppressLint("SetTextI18n")
    private fun fetchName(){
        val userUuid = intent.extras?.getString("user") ?: run {
            Log.e("ContactPage", "User UUID is null")
            finish() // Close the activity if user UUID is missing
            return
        }
        Firebase.firestore.collection("users")
            .document(userUuid)
            .get().addOnSuccessListener {
                val usr = it.toObject(User::class.java)
                Log.d("ContactPage",usr?.name ?: "No val")
                if (usr != null) {
                    naming.text = usr.name
                } else {
                    naming.text = "Name not available"
                    Log.e("ContactPage", "Name is null for user: $userUuid")
                }
            }
    }

}