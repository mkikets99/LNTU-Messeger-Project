package com.github.mkikets99.lntumessengerproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.authorization_layout)
//        setContent {
//            LNTUMessengerProjectTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
////                    Greeting(
////                        name = "Android",
////                        modifier = Modifier.padding(innerPadding)
////                    )
//                    TestMessenger(Modifier.padding(innerPadding))
//                }
//            }
//        }
//        test(param1 = "MyName") {
//            Log.d("MyTag","")
//        }
    }
}
