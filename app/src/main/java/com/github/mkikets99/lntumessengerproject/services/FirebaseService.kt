package com.github.mkikets99.lntumessengerproject.services

import android.content.Context
import androidx.core.util.Function
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.initialize
import java.util.concurrent.Callable

class FirebaseService {

    private lateinit var database: FirebaseFirestore

    companion object {
        val instance = FirebaseService()
    }

    private constructor(){
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
}