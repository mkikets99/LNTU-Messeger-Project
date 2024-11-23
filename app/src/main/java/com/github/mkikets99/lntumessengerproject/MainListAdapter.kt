package com.github.mkikets99.lntumessengerproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainListAdapter(
    private val items: MutableList<ChatRow>
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
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.naming.text = items[position].naming
        viewHolder.texting.text = items[position].texting
    }


    fun addItem(newItem: ChatRow) {
        items.add(newItem) // Add new item to the list
        notifyItemChanged(items.indexOf(newItem)) // Notify the adapter to refresh the ListView
    }
}