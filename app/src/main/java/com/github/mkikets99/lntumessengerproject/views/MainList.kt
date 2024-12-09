package com.github.mkikets99.lntumessengerproject.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.github.mkikets99.lntumessengerproject.ui.theme.CustomButton
import com.github.mkikets99.lntumessengerproject.ui.theme.CustomColorButton
import com.github.mkikets99.lntumessengerproject.ui.theme.LNTUMessengerProjectTheme

/*
*     Button(
        onClick = { navController.navigate("ChatPage") }
    ) {
        Text(
            text = "ChatPage"
        )
    }
    * */

@Preview
@Composable
fun MainList (/*navController: NavController TODO, model: ChatsModel*/){
    LNTUMessengerProjectTheme {
        ConstraintLayout (
            modifier = Modifier.fillMaxSize()
        ){
            val (topBar, chatList, addButton) = createRefs()
            Box(
                modifier = Modifier.height(50.dp)
                    .constrainAs(topBar) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(20.dp)
                    )
            ){
                Text(
                    text = "LNTU Messenger",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            LazyColumn(
                modifier = Modifier
                    .constrainAs(chatList) {
                        top.linkTo(topBar.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    }
            ) {
                /*items(model.chats) { item ->
                    ChatItem(item)
                }TODO*/
            }
            CustomColorButton(
                onClick = {/*TODO*/},
                contentPadding = PaddingValues(0.dp),
                shape = CircleShape,
                primaryColor = MaterialTheme.colorScheme.tertiary,
                secondaryColor = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.constrainAs(addButton) {
                    end.linkTo(parent.end, margin = 30.dp)
                    bottom.linkTo(parent.bottom, margin = 30.dp)
                }
                    .height(60.dp)
                    .width(60.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Chat",
                    modifier = Modifier.fillMaxSize(0.9f)
                )
            }
        }
    }
}