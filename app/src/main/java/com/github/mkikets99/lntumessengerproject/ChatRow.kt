package com.github.mkikets99.lntumessengerproject

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.github.mkikets99.lntumessengerproject.databinding.ChatRowsBinding

class ChatRow : ComponentActivity() {

    private lateinit var binder: ChatRowsBinding

    private lateinit var naming: TextView
    private lateinit var texting: TextView

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binder = ChatRowsBinding.inflate(layoutInflater)

        setContentView(binder.root)

        naming = binder.chatRowNaming
        texting = binder.chatRowTexting
    }

    public fun setNaming(string: String){
        naming.text = string
    }

    public fun setTexting(string: String){
        texting.text = string
    }
}