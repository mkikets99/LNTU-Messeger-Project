package com.github.mkikets99.lntumessengerproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.mkikets99.lntumessengerproject.classes.Chat
import com.github.mkikets99.lntumessengerproject.classes.User
import com.github.mkikets99.lntumessengerproject.databinding.ActivityMainListBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import java.util.ArrayList

class MainListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainListBinding
    private lateinit var adapter: MainListAdapter

    private var chats: ArrayList<Chat>? = null
    private var user: User? = null

    private val database: FirebaseFirestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        chats = ArrayList()
        adapter = MainListAdapter(this@MainListActivity,chats!!, user!!)
        binding.mainListRootListView.adapter = adapter

        database.collection("users")
            .get().addOnSuccessListener {

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
            }

        binding.mainListRootListView.setOnItemClickListener{ parent,view,position,id, ->
//            val intent = Intent(this, ContactSearchList::class.java)
//            startActivity(intent)
            Log.d("MainListActivity",view.toString())
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