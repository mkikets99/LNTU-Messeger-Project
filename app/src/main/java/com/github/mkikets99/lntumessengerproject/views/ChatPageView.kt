package com.github.mkikets99.lntumessengerproject.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.github.mkikets99.lntumessengerproject.ui.theme.CustomButton
import com.github.mkikets99.lntumessengerproject.ui.theme.CustomTextField
import com.github.mkikets99.lntumessengerproject.ui.theme.LNTUMessengerProjectTheme

@Preview
@Composable
fun ChatPageView(username: String = "User"/*, model: MessagesModel*/) {
    LNTUMessengerProjectTheme {
        ConstraintLayout (
            modifier = Modifier.fillMaxSize()
        ){
            val (topBar, chat, bottomBar, sendButton) = createRefs()

            ConstraintLayout (
                modifier = Modifier
                    .constrainAs(topBar){
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                }
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .height(50.dp)
            ) {

                val (button,text) = createRefs()
                Box(
                    modifier = Modifier
                        .constrainAs(button) {
                        top.linkTo(parent.top,)
                    }
                        .fillMaxHeight()
                        .aspectRatio(1.0f)
                ) {
                    Button(
                        modifier = Modifier.fillMaxSize(),
                        onClick = {}
                    ) {

                    }
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        "back_button",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    )
                }
                Text(
                    username,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    modifier = Modifier
                            .constrainAs(text) {
                                start.linkTo(button.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                end.linkTo(parent.end)
                                width = Dimension.fillToConstraints
                }
                )
            }
            var message by remember { mutableStateOf("") }
            CustomTextField(
                value = message,
                onValueChange = { newmsg -> message = newmsg },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .constrainAs(bottomBar) {
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(sendButton.start)
                        width = Dimension.fillToConstraints
                    }
                    .height(50.dp)
            )
            CustomButton(
                onClick = {},
                shape = RoundedCornerShape(20.dp),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier.constrainAs(sendButton) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                    .width(50.dp)
                    .height(50.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Send message",
                    modifier = Modifier.fillMaxSize(0.8f),
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
            LazyColumn(
                modifier = Modifier
                    .constrainAs(chat) {
                        top.linkTo(topBar.bottom)
                        bottom.linkTo(bottomBar.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    }
            ) {
                /*items(model.messages) { item ->
                    ChatItem(item)
                }TODO*/
            }
        }
    }
}