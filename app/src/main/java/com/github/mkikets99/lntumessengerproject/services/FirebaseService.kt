package com.github.mkikets99.lntumessengerproject.services

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.firebase.ui.auth.AuthUI
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import com.google.firebase.initialize

class FirebaseService {

    private lateinit var database: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    companion object {
        val instance = FirebaseService()
    }

    fun init(context: Context){
        Firebase.initialize(context)
        database = Firebase.firestore
        auth = FirebaseAuth.getInstance()
    }


    fun appendData(collection: String, data: Any, callback: (DocumentReference?, Exception?) -> Unit){
        database.collection(collection).add(data).addOnSuccessListener {
            callback(it,null)
        }.addOnFailureListener {
            callback(null,it)
        }
    }
    fun updateData(collection: String, document: String, data: Any, callback: (Void?, Exception?) -> Unit){
        database.collection(collection).document(document).set(data).addOnSuccessListener {
            callback(it,null)
        }.addOnFailureListener {
            callback(null,it)
        }
    }

    fun requestData(collection: String, callback: (QuerySnapshot?, Exception?) -> Unit){
        database.collection(collection).get().addOnSuccessListener {
            callback(it,null)
        }.addOnFailureListener {
            callback(null,it)
        }
    }
    fun requestData(collection: String, document: String,callback: (DocumentSnapshot?,Exception?) -> Unit ){
        database.collection(collection).document(document).get().addOnSuccessListener {
            callback(it,null)
        }.addOnFailureListener {
            callback(null,it)
        }
    }
    fun requestDataWithConditions(collection: String, conditions: Array<Pair<String,Pair<String,Any?>>>, callback: (QuerySnapshot?, Exception?) -> Unit){
        var query: Query = database.collection(collection)
        for (condition in conditions){
            when(condition.first){
                "AC" -> query = query.whereArrayContains(condition.second.first,condition.second.second?:"")
                "In" -> query = query.whereIn(condition.second.first,condition.second.second as MutableList<*>)
            }
        }
        query.get().addOnSuccessListener {
            callback(it,null)
        }.addOnFailureListener {
            callback(null,it)
        }
    }

    fun signForData(collection: String,callback: (QuerySnapshot?,Exception?) -> Unit ){
        database.collection(collection).addSnapshotListener{ snap,e->
            callback(snap,e)
        }
    }
    fun signForData(collection: String, document: String,callback: (DocumentSnapshot?,Exception?) -> Unit ){
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

    fun collectAuthData(): FirebaseAuth { return auth }
}