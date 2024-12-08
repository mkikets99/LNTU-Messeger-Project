package com.github.mkikets99.lntumessengerproject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
fun authActivity(navController: NavController){
    Column (Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = "LNTU messenger project",
            fontSize = 25.sp)
        var Name by remember { mutableStateOf("Name") }
        TextField(
            value = Name,
            onValueChange = { newname -> Name = newname
            })

        Button(onClick = {
            navController.navigate("MainList")
        }) { Text(text = "Login") }
    }
}
