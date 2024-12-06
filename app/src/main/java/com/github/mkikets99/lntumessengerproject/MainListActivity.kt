package com.github.mkikets99.lntumessengerproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mkikets99.lntumessengerproject.classes.Chat
import com.github.mkikets99.lntumessengerproject.classes.User
import com.github.mkikets99.lntumessengerproject.databinding.ActivityMainListBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import java.util.ArrayList

class MainListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainListBinding
    private lateinit var adapter: MainListAdapter

    private var chats: ArrayList<Chat>? = null
    private var user: User? = null

    private val database: FirebaseDatabase = Firebase.database("https://lntu-messenger-project-default-rtdb.europe-west1.firebasedatabase.app/")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        chats = ArrayList()
        binding.mainListRootListView.layoutManager = LinearLayoutManager(this)

        database.reference.child("users")
            .child(FirebaseAuth.getInstance().uid!!)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    user = snapshot.getValue(User::class.java)
                }

                override fun onCancelled(error: DatabaseError) {}
            })

        adapter = user!!.let { MainListAdapter(chats!!, it) }
        binding.mainListRootListView.adapter = adapter
        database.reference.child("chats").get().addOnSuccessListener {
            chats!!.clear()
                for (snapshot1 in it.children){
                    val chat: Chat? = snapshot1.getValue(Chat::class.java)
                    if (chat != null) {
                        if (chat.users.contains(user)) chat.let { adapter.addItem(it) }
                    }
                }

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