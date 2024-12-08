package com.github.mkikets99.lntumessengerproject.views

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.github.mkikets99.lntumessengerproject.controllers.MainListAdapter
import com.github.mkikets99.lntumessengerproject.classes.Chat
import com.github.mkikets99.lntumessengerproject.classes.User
import com.github.mkikets99.lntumessengerproject.databinding.ActivityMainListBinding
import com.github.mkikets99.lntumessengerproject.services.FirebaseService
import com.google.firebase.auth.FirebaseAuth
import java.util.ArrayList

class MainListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainListBinding
    private lateinit var adapter: MainListAdapter

    private var chats: ArrayList<Chat>? = null
    private var user: User? = null

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        chats = ArrayList()
        sharedPref = this.getSharedPreferences("settings", MODE_PRIVATE)
        // TODO: For later use with states
//        @Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE","NEVER_USED")
//        var uKey = ""
//        if (sharedPref.contains("user_key")) {
//            uKey = sharedPref.getString("user_key", "")!!
//        }

        FirebaseService.instance.requestData("users") { it, ex ->
            if (ex != null) {
                Log.e("MainListActivity", ex.message.toString())
                finish()
            }
            for (docs in it!!.documents) {
                if (docs.get("uuid") == FirebaseAuth.getInstance().uid) {
                    user = docs.toObject(User::class.java)
                    user?._key = docs.id
                    break
                }
            }
            FirebaseService.instance.requestDataWithConditions(
                "chats",
                arrayOf("AC" to ("users" to user!!._key)),
            ) { it1, e ->
                if (e != null) {
                    Log.e(this.javaClass.name, e.message.toString())
                    finish()
                }
                chats!!.clear()
                for (chatObj in it1!!.documents) {
                    val chat = chatObj.toObject(Chat::class.java)!!
                    chat._key = chatObj.id
                    chats!!.add(chat)
                    val ind = chats!!.size - 1
                    FirebaseService.instance.signForData("chats",chat._key!!) { snap, ex ->
                            if (ex != null) {
                                Log.e("MainListActivity", ex.message.toString())
                                finish()
                            }
                            chats!![ind] = snap!!.toObject(Chat::class.java)!!
                            adapter.notifyDataSetChanged()
                        }
                }
                adapter = MainListAdapter(this@MainListActivity, chats!!, user!!)
                binding.mainListRootListView.adapter = adapter
            }

        }
//                (FirebaseAuth.getInstance().uid!!)
//                    .(object : ValueEventListener {
//                        override fun onDataChange(snapshot: DataSnapshot) {
//                            user = snapshot.getValue(User::class.java)
//                            binding.mainListRootListView.adapter = adapter
//                            database.reference.child("chats").get().addOnSuccessListener {
//                                chats!!.clear()
//                                for (snapshot1 in it.children){
//                                    val chat: Chat? = snapshot1.getValue(Chat::class.java)
//                                    if (chat != null) {
//                                        if (chat.users?.contains(user?.uuid) == true) chat.let { adapter.items.add(it) }
//                                    }
//                                }
//
//                            }
//
//                        }
//
//                        override fun onCancelled(error: DatabaseError) {}
//                    })


        binding.mainListRootListView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, ChatRoom::class.java)
            val usersChat = chats!![position].users!!.clone() as ArrayList<*>
            usersChat.remove(user!!._key)

            intent.putExtra("cw", usersChat[0].toString())
            intent.putExtra("uia", user!!._key)
            startActivity(intent)

        }



        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null)
//                .setAnchorView(R.id.fab).show()
            val intent = Intent(this, ContactSearchList::class.java)
            startActivity(intent)
        }
    }
}