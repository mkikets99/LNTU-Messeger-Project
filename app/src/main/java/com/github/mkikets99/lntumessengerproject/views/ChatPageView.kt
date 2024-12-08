package com.github.mkikets99.lntumessengerproject.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.github.mkikets99.lntumessengerproject.ui.theme.LNTUMessengerProjectTheme

@Preview
@Composable
fun ChatPageView(username: String = "User") {
    LNTUMessengerProjectTheme {
        ConstraintLayout (
            modifier = Modifier.fillMaxSize()
        ){
            val (topBar, chat, bottombar) = createRefs()

            ConstraintLayout (
                modifier = Modifier.constrainAs(topBar){
                    top.linkTo(parent.top)
                }.fillMaxWidth().background(
                    MaterialTheme.colorScheme.primary
                ).height(50.dp)
            ) {

                val (button,text) = createRefs()
                Box(
                    modifier = Modifier.constrainAs(button) {
                        top.linkTo(parent.top,)
                    }.fillMaxHeight().aspectRatio(1.0f)
                ) {
                    Button(
                        modifier = Modifier.fillMaxSize(),
                        onClick = { }
                    ) {

                    }
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        "back_button",
                        modifier = Modifier.fillMaxSize().padding(10.dp)
                    )
                }
                Text(username,modifier = Modifier.constrainAs(text){
                    start.linkTo(button.end)
                }.fillMaxHeight())
            }
        }
    }
}