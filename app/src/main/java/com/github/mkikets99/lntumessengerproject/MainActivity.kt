package com.github.mkikets99.lntumessengerproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.mkikets99.lntumessengerproject.ui.theme.LNTUMessengerProjectTheme
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "AuthActivity", builder = {
                composable("SplashScreen"){
                    SplashScreen()
                }
                composable("AuthActivity"){
                    authActivity(navController)
                }
                composable("RegisterActivity"){
                    Reg()
                }
            })
            }
    }

}
//@Preview(showBackground = true)
@Composable
fun TestMessenger(
    modifier: Modifier = Modifier,
    gameViewModel: MainViewModel = viewModel()
) {

    Column(modifier = modifier.padding(16.dp)) {
        Button(onClick = {
            gameViewModel.getData()
        }) {
            Text(text = "Get data")
        }

        Button(onClick = {

        }) {
            Text(text = "Set data")
        }
    }
}