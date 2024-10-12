package com.github.mkikets99.lntumessengerproject

import android.os.Bundle
import android.util.Log
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
import com.github.mkikets99.lntumessengerproject.ui.theme.LNTUMessengerProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LNTUMessengerProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                    TestMessenger(Modifier.padding(innerPadding))
                }
            }
        }
//        test(param1 = "MyName") {
//            Log.d("MyTag","")
//        }
    }
}

@Composable
fun TestMessenger(modifier: Modifier = Modifier, viewModel: MainViewModel = viewModel()) {
    Column(modifier = modifier.padding(16.dp)) {
        Button(onClick = {
            // TODO: impl
        }) {
            Text(text = "Get data")
        }

        Button(onClick = {
            // TODO: impl
        }) {
            Text(text = "Set data")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LNTUMessengerProjectTheme {
        Greeting("Android")
    }
}