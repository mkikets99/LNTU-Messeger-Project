package com.github.mkikets99.lntumessengerproject.repositories

import com.github.mkikets99.lntumessengerproject.classes.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository {
    private val firestore = FirebaseFirestore.getInstance()
    private val usersCollection = firestore.collection("users")

    // Fetch all users
    suspend fun getUsers(): List<User> {
        return try {
            usersCollection.get().await().documents.mapNotNull { it.toObject(User::class.java) }
        } catch (e: Exception) {
            emptyList() // Handle exceptions (e.g., log or rethrow)
        }
    }

    // Add a user
    suspend fun addUser(user: User): Boolean {
        return try {
            usersCollection.document(user.uuid).set(user).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    // Add a user
    suspend fun updateUser(user: User): Boolean {
        return try {
            usersCollection.document(user.uuid).set(user).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    // Fetch a user by ID
    suspend fun getUserById(userId: String): User? {
        return try {
            usersCollection.document(userId).get().await().toObject(User::class.java)
        } catch (e: Exception) {
            null
        }
    }
}