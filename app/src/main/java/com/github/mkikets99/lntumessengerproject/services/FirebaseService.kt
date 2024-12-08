package com.github.mkikets99.lntumessengerproject.services

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.firebase.ui.auth.AuthUI
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.initialize

class FirebaseService {

    private lateinit var database: FirebaseFirestore

    companion object {
        val instance = FirebaseService()
    }

    fun init(context: Context){
        Firebase.initialize(context)
        database = Firebase.firestore
    }

    fun requestData(collection: String,callback: (Any?,Exception?) -> Void ){
        database.collection(collection).get().addOnSuccessListener {
            callback(it,null)
        }.addOnFailureListener {
            callback(null,it)
        }
    }
    fun requestData(collection: String, document: String,callback: (Any?,Exception?) -> Void ){
        database.collection(collection).document(document).get().addOnSuccessListener {
            callback(it,null)
        }.addOnFailureListener {
            callback(null,it)
        }
    }
    fun signForData(collection: String,callback: (Any?,Exception?) -> Void ){
        database.collection(collection).addSnapshotListener{ snap,e->
            callback(snap,e)
        }
    }
    fun signForData(collection: String, document: String,callback: (Any?,Exception?) -> Void ){
        database.collection(collection).document(document).addSnapshotListener{ snap,e->
            callback(snap,e)
        }
    }

    fun requestAuth(callback : ActivityResultLauncher<Intent>){
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()

        callback.launch(signInIntent)
    }
}