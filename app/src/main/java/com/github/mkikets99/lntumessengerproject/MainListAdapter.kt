package com.github.mkikets99.lntumessengerproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class MainListAdapter(
    private val context: Context,
    private val items: MutableList<ChatRow>
) : BaseAdapter() {
    override fun getCount(): Int = items.size
    override fun getItem(p0: Int): Any = items[p0]
    override fun getItemId(p0: Int): Long = p0.toLong()
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.chat_rows, parent, false)

        // Get the current item
        val item = getItem(position) as ChatRow

        // Bind data to the views
        val naming: TextView = view.findViewById(R.id.chat_row_naming)
        val texting: TextView = view.findViewById(R.id.chat_row_texting)

        naming.text = item.naming
        texting.text = item.texting

        return view
    }

    fun addItem(newItem: ChatRow) {
        items.add(newItem) // Add new item to the list
        notifyDataSetChanged() // Notify the adapter to refresh the ListView
    }
}