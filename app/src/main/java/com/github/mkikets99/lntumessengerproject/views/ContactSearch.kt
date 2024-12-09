package com.github.mkikets99.lntumessengerproject.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
fun ContactSearchView (/*navController: NavController TODO, model: ContactsModel*/) {
    LNTUMessengerProjectTheme {
        ConstraintLayout (
            modifier = Modifier.fillMaxSize()
        ){
            val (topBar, textField, contactList) = createRefs()
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
                    text = "Contacts",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.titleLarge
                )
                CustomButton(
                    onClick = {/*TODO*/},
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.align(Alignment.CenterStart)
                        .fillMaxHeight()
                        .aspectRatio(1f)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back Button",
                        modifier = Modifier.fillMaxSize(0.8f)
                    )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .constrainAs(contactList) {
                        top.linkTo(topBar.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    }
            ) {
                /*items(model.contacts) { item ->
                    ChatItem(item)
                }TODO*/
            }
            var contactName by remember { mutableStateOf("Search contact...") }
            CustomTextField(
                value = contactName,
                onValueChange = { newContactName: String -> contactName = newContactName },
                modifier = Modifier
                    .constrainAs(textField) {
                        start.linkTo(parent.start, margin = 50.dp)
                        bottom.linkTo(parent.bottom, margin = 50.dp)
                        end.linkTo(parent.end, margin = 50.dp)
                        width = Dimension.fillToConstraints
                    }
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(20.dp)
                        )
                    .height(50.dp),
                shape = RoundedCornerShape(20.dp)
            )
        }
    }
}