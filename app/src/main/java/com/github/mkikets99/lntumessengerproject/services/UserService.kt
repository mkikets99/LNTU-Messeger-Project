package com.github.mkikets99.lntumessengerproject.services

import com.github.mkikets99.lntumessengerproject.classes.User
import com.github.mkikets99.lntumessengerproject.repositories.UserRepository

class UserService(private val userRepository: UserRepository) {

    // Get all users
    suspend fun fetchUsers(): List<User> {
        return userRepository.getUsers()
    }

    // Create a user (e.g., enforce unique email logic)
    suspend fun createUser(user: User): Result<String> {
        val existingUsers = userRepository.getUsers()
        if (existingUsers.any { it.name == user.name }) {
            return Result.failure(Exception("User already exists"))
        }
        return if (userRepository.addUser(user)) {
            Result.success("User added successfully")
        } else {
            Result.failure(Exception("Failed to add user"))
        }
    }
}