package com.github.mkikets99.lntumessengerproject.views

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun MainList (navController: NavController){
    Text(text = "flfkfk")
    Button(onClick = {
        navController.navigate("ChatPage")
    }) { Text(text = "ChatPage") }
}