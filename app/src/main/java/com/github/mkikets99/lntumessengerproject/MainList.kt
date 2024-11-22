package com.github.mkikets99.lntumessengerproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mkikets99.lntumessengerproject.databinding.ActivityMainListBinding

class MainList : AppCompatActivity() {

    private lateinit var binding: ActivityMainListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val startList = mutableListOf(
            ChatRow("man","sus")
        )
        val adapter = MainListAdapter(this, startList)
        binding.mainListRootListView.adapter = adapter

        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null)
//                .setAnchorView(R.id.fab).show()
            val newRow = ChatRow("sus","amogus")
            adapter.addItem(newRow)
        }
    }
}