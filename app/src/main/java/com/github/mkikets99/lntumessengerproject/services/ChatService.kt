package com.github.mkikets99.lntumessengerproject.services

import com.github.mkikets99.lntumessengerproject.classes.Chat
import com.github.mkikets99.lntumessengerproject.repositories.ChatRepository

class ChatService(private val chatRepository: ChatRepository) {

    // Get all users
    suspend fun fetchChats(): List<Chat> {
        return chatRepository.getChats()
    }

    // Create a user (e.g., enforce unique email logic)
    suspend fun createChat(chat: Chat): Result<String> {
        val existingChats = chatRepository.getChats()
        if (existingChats.any { it.users!!.contains(chat.users!![0]) && it.users.contains(chat.users[1]) }) {
            return Result.failure(Exception("Chat already exists"))
        }
        return if (chatRepository.addChat(chat)) {
            Result.success("User added successfully")
        } else {
            Result.failure(Exception("Failed to add user"))
        }
    }
}