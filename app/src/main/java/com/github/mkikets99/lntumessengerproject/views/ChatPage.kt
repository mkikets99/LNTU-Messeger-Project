package com.github.mkikets99.lntumessengerproject.views

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ChatPage(navController: NavController) {
    Column() {
        Row {
            Button(onClick = {
                navController.navigate("MainList") {
                    popUpTo("MainList"){inclusive = true}
                }
            }){
                Text(text = "Назад")
                }
            LazyColumn(Modifier.fillMaxSize()) {
            }
            Row {
                Modifier.fillMaxSize()
                var Name by remember { mutableStateOf("Name") }
                TextField(
                    value = Name,
                    onValueChange = { newname ->
                        Name = newname
                    })
                Button(onClick = {
                    navController.navigate("MainList")
                }) { Text(text = "send") }
            }

        }
    }
}