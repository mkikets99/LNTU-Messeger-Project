package com.github.mkikets99.lntumessengerproject.controllers

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.mkikets99.lntumessengerproject.AuthActivity
import com.github.mkikets99.lntumessengerproject.views.ChatPage
import com.github.mkikets99.lntumessengerproject.views.MainList

@Composable
fun IntNavController(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "auth_screen"
    ) {
            composable("AuthActivity"){
                AuthActivity(navController)
            }
            composable("MainList"){
                MainList(navController)
            }
            composable("ChatPage"){
                ChatPage(navController)
            }
    }
}