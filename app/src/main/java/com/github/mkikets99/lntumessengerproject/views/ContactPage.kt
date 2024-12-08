package com.github.mkikets99.lntumessengerproject.views

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
import com.github.mkikets99.lntumessengerproject.R
import com.github.mkikets99.lntumessengerproject.classes.User
import com.github.mkikets99.lntumessengerproject.services.FirebaseService

class ContactPage : AppCompatActivity() {

    private lateinit var naming: TextView
    private var name: String? = null
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

        addBtn.setOnClickListener {
            currentLookUser!!._key?.let { it1 -> currentUser!!.friends.add(it1) }
            currentUser!!._key?.let { it1 -> currentLookUser!!.friends.add(it1) }
            currentLookUser!!._key?.let { it1 ->
                FirebaseService.instance.updateData(
                    "users", it1,
                    currentLookUser!!
                ) { _, ex ->
                    if (ex != null) {
                        Log.e(this.javaClass.name, ex.message.toString())
                        finish()
                    }
                }
            }
            currentUser!!._key?.let { it1 ->
                FirebaseService.instance.updateData(
                    "users", it1,
                    currentUser!!
                ) { _, ex ->
                    if (ex != null) {
                        Log.e(this.javaClass.name, ex.message.toString())
                        finish()
                    }
                }
            }
            addBtn.visibility = View.GONE
            msgBtn.visibility = View.VISIBLE
            dltBtn.visibility = View.VISIBLE

        }
        bkBtn.setOnClickListener {
            finish()
        }
        msgBtn.setOnClickListener {
            val intent = Intent(this, ChatRoom::class.java)
            intent.putExtra("cw", currentLookUser!!._key)
            intent.putExtra("uia", currentUser!!._key)
            startActivity(intent)
        }
        dltBtn.setOnClickListener {
            currentLookUser!!._key?.let { it1 -> currentUser!!.friends.remove(it1) }
            currentUser!!._key?.let { it1 -> currentLookUser!!.friends.remove(it1) }
            currentLookUser!!._key?.let { it1 ->
                FirebaseService.instance.updateData(
                    "users", it1,
                    currentLookUser!!
                ) { _, ex ->
                    if (ex != null) {
                        Log.e(this.javaClass.name, ex.message.toString())
                        finish()
                    }
                }
            }
            currentUser!!._key?.let { it1 ->
                FirebaseService.instance.updateData(
                    "users", it1,
                    currentUser!!
                ) { _, ex ->
                    if (ex != null) {
                        Log.e(this.javaClass.name, ex.message.toString())
                        finish()
                    }
                }
            }
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
        FirebaseService.instance.requestData("users", userUuid) { it, ex ->
            if (ex != null) {
                Log.e(this.javaClass.name, ex.message.toString())
                finish()
            }
            currentLookUser = it!!.toObject(User::class.java)
            currentLookUser!!._key = it.id
            Log.d("ContactPage", currentLookUser?.name ?: "No val")
            if (currentLookUser != null) {
                naming.text = currentLookUser!!.name

                FirebaseService.instance.requestData("users") { it1, ex1 ->
                    if (ex1 != null) {
                        Log.e(this.javaClass.name, ex1.message.toString())
                        finish()
                    }

                    for (docs in it1!!.documents) {
                        if (docs.get("uuid") == FirebaseService.instance.collectAuthData().uid) {
                            currentUser = docs.toObject(User::class.java)
                            currentUser?._key = docs.id
                            break
                        }
                    }
                    if (!currentUser!!.friends.contains(currentLookUser!!._key)) {
                        msgBtn.visibility = View.GONE
                        dltBtn.visibility = View.GONE
                    } else {
                        addBtn.visibility = View.GONE
                    }
                }
            } else {
                naming.text = getString(R.string.name_not_available)
                Log.e("ContactPage", "Name is null for user: $userUuid")
                finish()
            }
        }



        naming = findViewById<TextView>(R.id.naming)
        naming.text = name
        // Fetch data from Firebase
    }


}