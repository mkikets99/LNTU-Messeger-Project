package com.github.mkikets99.lntumessengerproject

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.github.mkikets99.lntumessengerproject.classes.Chat
import com.github.mkikets99.lntumessengerproject.classes.User
import kotlin.collections.ArrayList

@Suppress("UNCHECKED_CAST")
class MainListAdapter(
    private val items: ArrayList<Chat>,
    private val user: User
) : RecyclerView.Adapter<MainListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val naming: TextView = view.findViewById(R.id.chat_row_naming)
        val texting: TextView = view.findViewById(R.id.chat_row_texting)
    }

    override fun getItemCount(): Int = items.size
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.chat_rows, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemId(p0: Int): Long = p0.toLong()
    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val users: ArrayList<User> = items[position].users.clone() as ArrayList<User>
        users.remove(user)
        viewHolder.naming.text = users[0].name
        viewHolder.texting.text = items[position].messages.last.message
    }


    fun addItem(newItem: Chat) {
        items.add(newItem) // Add new item to the list
        notifyItemChanged(items.indexOf(newItem)) // Notify the adapter to refresh the ListView
    }
}