package com.github.mkikets99.lntumessengerproject.repositories

import com.github.mkikets99.lntumessengerproject.classes.Chat
import com.github.mkikets99.lntumessengerproject.classes.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ChatRepository {
    private val firestore = FirebaseFirestore.getInstance()
    private val usersCollection = firestore.collection("chats")

    // Fetch all users
    suspend fun getChats(): List<Chat> {
        return try {
            usersCollection.get().await().documents.mapNotNull { it.toObject(Chat::class.java) }
        } catch (e: Exception) {
            emptyList() // Handle exceptions (e.g., log or rethrow)
        }
    }

    // Add a user
    suspend fun addChat(chat: Chat): Boolean {
        return try {
            usersCollection.document(chat.chatId).set(chat).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    // Fetch a user by ID
    suspend fun getUserById(chatId: String): Chat? {
        return try {
            usersCollection.document(chatId).get().await().toObject(Chat::class.java)
        } catch (e: Exception) {
            null
        }
    }
}