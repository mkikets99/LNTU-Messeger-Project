package com.github.mkikets99.lntumessengerproject.controllers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.github.mkikets99.lntumessengerproject.R
import com.github.mkikets99.lntumessengerproject.classes.Chat
import com.github.mkikets99.lntumessengerproject.classes.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainListAdapter(
    private val context: Context,
    val items: MutableList<Chat>,
    private val user: User
) : BaseAdapter() {
    override fun getCount(): Int = items.size
    override fun getItem(p0: Int): Any = items[p0]
    override fun getItemId(p0: Int): Long = p0.toLong()
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.chat_rows, parent, false)

        // Get the current item
        val item = getItem(position) as Chat

        // Bind data to the views
        val naming: TextView = view.findViewById(R.id.chat_row_naming)
        val texting: TextView = view.findViewById(R.id.chat_row_texting)

        item.users!!.remove(this.user._key)
        Firebase.firestore.collection("users").document(item.users[0]).get().addOnSuccessListener {
            naming.text = it.getString("name")
        }
        texting.text = item.messages!![item.messages.size-1].message

        return view
    }

}