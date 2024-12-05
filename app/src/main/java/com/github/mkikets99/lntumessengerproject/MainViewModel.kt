package com.github.mkikets99.lntumessengerproject

import androidx.lifecycle.ViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainViewModel: ViewModel() {
    val repo = FirebaseRepository()

    fun getData() {
        //TODO("Not yet implemented")
        repo.getData()
    }

    fun setData() {
        repo.setData()
            val database = Firebase.database
            val myRef = database.getReference("")

            myRef.setValue("Hello, World!укквв")
    }
}