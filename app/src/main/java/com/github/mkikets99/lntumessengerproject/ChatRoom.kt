package com.github.mkikets99.lntumessengerproject

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.mkikets99.lntumessengerproject.classes.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ChatRoom : AppCompatActivity() {
    private lateinit var userIAm: User
    private lateinit var userIAmWith: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val uia = intent.getStringExtra("uia")
        val cw = intent.getStringExtra("cw")

        setContentView(R.layout.chat_room)

        val uname = findViewById<TextView>(R.id.username_title)
        val backbut = findViewById<ImageButton>(R.id.button_arrow_back)
        val messagesList = findViewById<ListView>(R.id.chat)
        val message = findViewById<EditText>(R.id.messageEd)
        val send = findViewById<ImageButton>(R.id.sendMessage_b)

        messagesList.adapter = ArrayAdapter<String>(this,0)

        backbut.setOnClickListener{
            finish()
        }
        send.setOnClickListener{
            Toast.makeText(this@ChatRoom,message.text,Toast.LENGTH_SHORT).show()
        }

        Firebase.firestore.collection("users").document(uia!!).get().addOnSuccessListener {
            userIAm = it.toObject(User::class.java)!!
            Firebase.firestore.collection("users").document(cw!!).get().addOnSuccessListener {
                userIAmWith = it.toObject(User::class.java)!!
                uname.text = userIAmWith.name
            }
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
}