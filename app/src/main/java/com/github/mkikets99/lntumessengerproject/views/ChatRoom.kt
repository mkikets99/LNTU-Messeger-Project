package com.github.mkikets99.lntumessengerproject.views

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
import com.github.mkikets99.lntumessengerproject.R
import com.github.mkikets99.lntumessengerproject.classes.Chat
import com.github.mkikets99.lntumessengerproject.classes.Message
import com.github.mkikets99.lntumessengerproject.classes.User
import com.github.mkikets99.lntumessengerproject.services.FirebaseService

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
        val backButton = findViewById<ImageButton>(R.id.button_arrow_back)
        val messagesList = findViewById<ListView>(R.id.chat)
        val message = findViewById<EditText>(R.id.messageEd)
        val send = findViewById<ImageButton>(R.id.sendMessage_b)

        messagesList.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)

        backButton.setOnClickListener {
            finish()
        }
        send.setOnClickListener {
            val msg = Message(userIAm._key!!, message.text.toString())
            currentChat!!.messages!!.add(msg)
            FirebaseService.instance.updateData(
                "chats",
                currentChat!!._key!!,
                currentChat!!
            ) { _, ex ->
                if (ex != null) {
                    Log.e(this.javaClass.name, ex.message.toString())
                    finish()
                }
                //(messagesList.adapter as ArrayAdapter<String>).add(buildMessage(msg))
                //(messagesList.adapter as ArrayAdapter<*>).notifyDataSetChanged()
            }
        }

        FirebaseService.instance.requestData("users", uia!!) { it, ex ->
            if (ex != null) {
                Log.e(this.javaClass.name, ex.message.toString())
                finish()
            }
            userIAm = it!!.toObject(User::class.java)!!
            FirebaseService.instance.requestData("users", cw!!) { it1, ex1 ->
                if (ex1 != null) {
                    Log.e(this.javaClass.name, ex1.message.toString())
                    finish()
                }
                userIAmWith = it1!!.toObject(User::class.java)!!
                uname.text = userIAmWith.name
                FirebaseService.instance.requestDataWithConditions(
                    "chats",
                    arrayOf("AC" to ("users" to userIAm._key!!))
                ) { it2, ex2 ->
                    if (ex2 != null) {
                        Log.e(this.javaClass.name, ex2.message.toString())
                        finish()
                    }
                    for (docs in it2!!.documents) {
                        val cha = docs.toObject(Chat::class.java)!!
                        if (cha.users!!.contains(userIAmWith._key!!)) {
                            currentChat = cha
                            FirebaseService.instance.signForData("chats", docs.id) { snap, e ->
                                if (e != null) {
                                    Log.e(this.javaClass.name, e.message.toString())
                                    finish()
                                }
                                if (snap != null && snap.exists()) {
                                    currentChat = snap.toObject(Chat::class.java)!!
                                    (messagesList.adapter as ArrayAdapter<*>).clear()
                                    for (msg in buildMessages()) {
                                        (messagesList.adapter as ArrayAdapter<String>).add(msg)
                                    }
                                    (messagesList.adapter as ArrayAdapter<*>).notifyDataSetChanged()
                                }
                            }
                            break
                        }
                    }
                    if (currentChat == null) {
                        currentChat = Chat()
                        currentChat!!.users!!.add(userIAm._key!!)
                        currentChat!!.users!!.add(userIAmWith._key!!)
                        FirebaseService.instance.appendData("chats", currentChat!!) { it3, ex3 ->

                            if (ex3 != null) {
                                Log.e(this.javaClass.name, ex3.message.toString())
                                finish()
                            }
                            currentChat!!._key = it3!!.id
                        }
                    } else {
                        currentChat = it2.documents[0].toObject(Chat::class.java)!!
                    }
                    for (msg in buildMessages()) {
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
        for (msg in currentChat!!.messages!!) {
            out.add(buildMessage(msg))
        }
        return out
    }

    private fun buildMessage(msg: Message): String {
        return "" + (if (userIAm._key == msg.from) userIAm.name else if (userIAmWith._key == msg.from) userIAmWith.name else "Error") + " : " + msg.message
    }
}