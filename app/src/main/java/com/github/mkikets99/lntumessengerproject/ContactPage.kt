package com.github.mkikets99.lntumessengerproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
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
    private var currentLookUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContentView(R.layout.activity_contact_page)

        val msgBtn = findViewById<Button>(R.id.b_chat_contact)
        val dltBtn = findViewById<Button>(R.id.b_remove_contact)
        val addBtn = findViewById<Button>(R.id.b_add_contact)
        val bkBtn = findViewById<ImageButton>(R.id.back_b)

        addBtn.setOnClickListener{
            currentLookUser!!._key?.let { it1 -> currentUser!!.friends.add(it1) }
            currentUser!!._key?.let { it1 -> currentLookUser!!.friends.add(it1) }
            currentLookUser!!._key?.let { it1 -> Firebase.firestore.collection("users").document(it1).set(
                currentLookUser!!
            ) }
            currentUser!!._key?.let { it1 -> Firebase.firestore.collection("users").document(it1).set(
                currentUser!!
            ) }
            addBtn.visibility = View.GONE
            msgBtn.visibility = View.VISIBLE
            dltBtn.visibility = View.VISIBLE

        }
        bkBtn.setOnClickListener{
            finish()
        }
        msgBtn.setOnClickListener{
            val intent = Intent(this, ChatRoom::class.java)
            intent.putExtra("cw",currentLookUser!!._key)
            intent.putExtra("uia",currentUser!!._key)
            startActivity(intent)
        }
        dltBtn.setOnClickListener{
            currentLookUser!!._key?.let { it1 -> currentUser!!.friends.remove(it1) }
            currentUser!!._key?.let { it1 -> currentLookUser!!.friends.remove(it1) }
            currentLookUser!!._key?.let { it1 -> Firebase.firestore.collection("users").document(it1).set(
                currentLookUser!!
            ) }
            currentUser!!._key?.let { it1 -> Firebase.firestore.collection("users").document(it1).set(
                currentUser!!
            ) }
            addBtn.visibility = View.VISIBLE
            msgBtn.visibility = View.GONE
            dltBtn.visibility = View.GONE

        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val userUuid = intent.extras?.getString("user") ?: run {
            Log.e("ContactPage", "User UUID is null")
            finish() // Close the activity if user UUID is missing
            return
        }
        Firebase.firestore.collection("users")
            .document(userUuid)
            .get().addOnSuccessListener {
                currentLookUser = it.toObject(User::class.java)
                currentLookUser!!._key = it.id
                Log.d("ContactPage",currentLookUser?.name ?: "No val")
                if (currentLookUser != null) {
                    naming.text = currentLookUser!!.name

                    Firebase.firestore.collection("users")
                        .get().addOnSuccessListener {
                            for(docs in it.documents){
                                if(docs.get("uuid") == FirebaseAuth.getInstance().uid){
                                    currentUser = docs.toObject(User::class.java)
                                    currentUser?._key = docs.id
                                    break
                                }
                            }
                            if (!currentUser!!.friends.contains(currentLookUser!!._key)){
                                msgBtn.visibility = View.GONE
                                dltBtn.visibility = View.GONE
                            }else{
                                addBtn.visibility = View.GONE
                            }
                        }
                } else {
                    naming.text = "Name not available"
                    Log.e("ContactPage", "Name is null for user: $userUuid")
                    finish()
                }
            }



        naming = findViewById<TextView>(R.id.naming)
        naming.text = name
        // Fetch data from Firebase
    }


}