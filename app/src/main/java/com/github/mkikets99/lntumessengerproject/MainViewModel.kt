package com.github.mkikets99.lntumessengerproject

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val repo = FirebaseRepository()

    fun getData() {
        //TODO("Not yet implemented")
        repo.getData()
    }

    fun setData() {
        repo.setData()
        //TODO("Not yet implemented")
    }
}