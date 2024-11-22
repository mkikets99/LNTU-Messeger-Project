package com.github.mkikets99.lntumessengerproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mkikets99.lntumessengerproject.databinding.ActivityMainListBinding
import com.google.android.material.snackbar.Snackbar

class MainList : AppCompatActivity() {

    private lateinit var binding: ActivityMainListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }
    }
}