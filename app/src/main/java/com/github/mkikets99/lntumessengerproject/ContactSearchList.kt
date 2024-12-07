package com.github.mkikets99.lntumessengerproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import com.github.mkikets99.lntumessengerproject.classes.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class ContactSearchList : AppCompatActivity() {
    private var users : ArrayList<User>? = null
    private var user : User? = null

    private val database: FirebaseFirestore = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contact_search_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        users = ArrayList()
        val mListView = findViewById<ListView>(R.id.contactList)
        val searchBar = findViewById<EditText>(R.id.searchField)
        val btn_b = findViewById<ImageButton>(R.id.backButton)

        btn_b.setOnClickListener{
            finish()
        }

        var arrAdapt: ArrayAdapter<*> = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, users!!
        )

        searchBar.doOnTextChanged { text, start, before, count ->
           // Toast.makeText(this@ContactSearchList,text,Toast.LENGTH_SHORT).show()
            var items = users!!.clone() as ArrayList<User>
            items = ArrayList(items.filter { u ->
                u.name.toLowerCase(Locale.current).contains(text.toString().toLowerCase(Locale.current))
            }.toList())
            arrAdapt = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, items
            )
            mListView.adapter = arrAdapt
            arrAdapt.notifyDataSetChanged()
        }

        mListView.adapter = arrAdapt
        database.collection("users")
            .get().addOnSuccessListener {
                    for (snapshot1 in it.documents){
                        val user: User? = snapshot1.toObject(User::class.java)
                        if(user!!.uuid.equals(FirebaseAuth.getInstance().uid)) {
                            this.user = user
                            break
                        }
                    }
            }
        mListView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "Clicked item : $position", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ContactPage::class.java)
            intent.putExtra("user", users!![position]._key)
            this.startActivity(intent)
        }
        database.collection("users").get().addOnSuccessListener{
                users!!.clear()
                for (snapshot1 in it.documents){
                    val user: User? = snapshot1.toObject(User::class.java)
                    user!!._key = snapshot1.id
                    if(!user.uuid.equals(FirebaseAuth.getInstance().uid)) users!!.add(user)
                }
                arrAdapt.notifyDataSetChanged()
            }.addOnFailureListener {
            it.message?.let { it1 -> Log.e("FirebaseDatabase", it1) }
        }

    }
}