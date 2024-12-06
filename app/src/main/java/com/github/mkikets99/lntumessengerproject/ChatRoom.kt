package com.github.mkikets99.lntumessengerproject

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.mkikets99.lntumessengerproject.classes.Chat
import com.github.mkikets99.lntumessengerproject.classes.Message
import com.github.mkikets99.lntumessengerproject.classes.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ChatRoom : AppCompatActivity() {
    private lateinit var userIAm: User
    private lateinit var userIAmWith: User
    private var currentChat: Chat? = null

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

        messagesList.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1)

        backbut.setOnClickListener{
            finish()
        }
        send.setOnClickListener{
            val msg = Message(userIAm._key!!, message.text.toString())
            currentChat!!.messages!!.add(msg)
            Firebase.firestore.collection("chats")
                .document(currentChat!!._key!!).set(currentChat!!).addOnSuccessListener {
                    (messagesList.adapter as ArrayAdapter<String>).add(buildMessage(msg))
                    (messagesList.adapter as ArrayAdapter<*>).notifyDataSetChanged()
            }
        }

        Firebase.firestore.collection("users").document(uia!!).get().addOnSuccessListener {
            userIAm = it.toObject(User::class.java)!!
            Firebase.firestore.collection("users").document(cw!!).get().addOnSuccessListener {
                userIAmWith = it.toObject(User::class.java)!!
                uname.text = userIAmWith.name
                Firebase.firestore.collection("chats").whereArrayContains("users",
                    userIAm._key!!).get().addOnSuccessListener {
                        for(docs in it.documents){
                            val cha = docs.toObject(Chat::class.java)!!
                            if(cha.users!!.contains(userIAmWith._key!!)){
                                currentChat = cha
                                break
                            }
                        }
                        if(currentChat == null){
                            currentChat = Chat()
                            currentChat!!.users!!.add(userIAm._key!!)
                            currentChat!!.users!!.add(userIAmWith._key!!)
                            Firebase.firestore.collection("chats").add(currentChat!!).addOnSuccessListener {
                                currentChat!!._key = it.id
                            }.addOnFailureListener { ex ->
                                Log.e("ChatRoomError",ex.message!!)
                            }
                        }else{
                            currentChat = it.documents[0].toObject(Chat::class.java)!!
                        }
                    for(msg in buildMessages()){
                        (messagesList.adapter as ArrayAdapter<String>).add(msg)
                    }
                    (messagesList.adapter as ArrayAdapter<*>).notifyDataSetChanged()
                }
            }
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
    private fun buildMessages(): ArrayList<String> {
        val out = ArrayList<String>()
        for(msg in currentChat!!.messages!!){
            out.add(buildMessage(msg))
        }
        return out
    }
    private fun buildMessage(msg: Message): String {
        return ""+(if (userIAm._key==msg.from) userIAm.name else if(userIAmWith._key==msg.from) userIAmWith.name else "Error") + " : " + msg.message
    }
}