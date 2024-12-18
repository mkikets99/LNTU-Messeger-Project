package com.github.mkikets99.lntumessengerproject.controllers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.mkikets99.lntumessengerproject.AuthActivity
import com.github.mkikets99.lntumessengerproject.screens.ChatListPage
import com.github.mkikets99.lntumessengerproject.screens.ChatPage
import com.github.mkikets99.lntumessengerproject.screens.ContactListPage
import com.github.mkikets99.lntumessengerproject.screens.ContactPage
import com.github.mkikets99.lntumessengerproject.views.GoogleSignInViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntNavController()
        }
    }
}
@Composable
fun IntNavController() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val googleSignInViewModel = GoogleSignInViewModel()

    NavHost(
        navController = navController,
        startDestination = "AuthViews"
    ) {
        composable(route = "AuthViews") {
            AuthActivity {
                googleSignInViewModel.handleGoogleSignIn(context,navController)
            }
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