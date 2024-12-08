package com.github.mkikets99.lntumessengerproject.controllers

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.mkikets99.lntumessengerproject.screens.AuthPage
import com.github.mkikets99.lntumessengerproject.screens.ChatListPage
import com.github.mkikets99.lntumessengerproject.screens.ChatPage
import com.github.mkikets99.lntumessengerproject.screens.ContactListPage
import com.github.mkikets99.lntumessengerproject.screens.ContactPage
import com.github.mkikets99.lntumessengerproject.views.MainList

@Composable
fun IntNavController(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "AuthPage"
    ) {
            composable("AuthPage"){
                AuthPage(navController)
            }
            composable("ChatListPage"){
                ChatListPage(navController)
            }
            composable("ContactListPage"){
                ContactListPage(navController)
            }
            composable("ChatPage"){
                ChatPage(navController)
            }
            composable("ContactPage"){
                ContactPage(navController)
            }
    }
}