package com.github.mkikets99.lntumessengerproject.views

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
import com.github.mkikets99.lntumessengerproject.R
import com.github.mkikets99.lntumessengerproject.classes.User
import com.github.mkikets99.lntumessengerproject.services.FirebaseService

class ContactSearchList : AppCompatActivity() {
    private var users: ArrayList<User>? = null
    private var user: User? = null


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

        btn_b.setOnClickListener {
            finish()
        }

        var arrAdapt: ArrayAdapter<*> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, users!!
        )

        searchBar.doOnTextChanged { text, _, _, _ ->
            // Toast.makeText(this@ContactSearchList,text,Toast.LENGTH_SHORT).show()
            FirebaseService.instance.requestData("users") { it, ex ->
                if (ex != null) {
                    Log.e(this.javaClass.name, ex.message.toString())
                    finish()
                }
                val items = java.util.ArrayList<String>()
                for (snapshot1 in it!!.documents) {
                    val user: User = snapshot1.toObject(User::class.java)!!
                    if (user.name.toLowerCase(Locale.current)
                            .contains(text.toString().toLowerCase(Locale.current))) {
                        items.add(user.toString())

                    }
                }
                arrAdapt = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1, items
                )
                mListView.adapter = arrAdapt
                arrAdapt.notifyDataSetChanged()
            }

        }

        mListView.adapter = arrAdapt
        FirebaseService.instance.requestData("users") { it, ex ->
            if (ex != null) {
                Log.e(this.javaClass.name, ex.message.toString())
                finish()
            }
            for (snapshot1 in it!!.documents) {
                val user: User? = snapshot1.toObject(User::class.java)
                if (user!!.uuid == FirebaseService.instance.collectAuthData().uid) {
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
        FirebaseService.instance.requestData("users") { it, ex ->
            if (ex != null) {
                Log.e(this.javaClass.name, ex.message.toString())
                finish()
            }
            users!!.clear()
            for (snapshot1 in it!!.documents) {
                val user: User? = snapshot1.toObject(User::class.java)
                user!!._key = snapshot1.id
                if (!user.uuid.equals(FirebaseService.instance.collectAuthData().uid)) users!!.add(user)
            }
            arrAdapt.notifyDataSetChanged()

        }

    }
}