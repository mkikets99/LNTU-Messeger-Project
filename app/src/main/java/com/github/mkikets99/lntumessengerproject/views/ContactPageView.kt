package com.github.mkikets99.lntumessengerproject.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.mkikets99.lntumessengerproject.ui.theme.CustomButton
import com.github.mkikets99.lntumessengerproject.ui.theme.CustomColorButton
import com.github.mkikets99.lntumessengerproject.ui.theme.LNTUMessengerProjectTheme
import com.google.android.material.shape.ShapeAppearanceModel

@Composable
fun ContactPageView(username: String = "user") {
    LNTUMessengerProjectTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        )
        {
            Text(
                modifier = Modifier.offset(y = 20.dp)
                    .align(Alignment.TopCenter),
                text = "Contact information",
                fontSize = 23.sp,
                textAlign = TextAlign.Center
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .height(60.dp)
                    .width(60.dp),) {
                CustomColorButton(
                    onClick = {},
                    modifier = Modifier.fillMaxSize(),/*,
                    shape = RoundedCornerShape(0.dp)*/
                    primaryColor = Color(0xFF264ca0),
                    secondaryColor = Color(0xFF01a288)
                )
                {}
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back button",
                    modifier = Modifier.fillMaxSize(),
                    tint = Color(0xFF01a288)
                )
            }
            Text(
                text = username,
                fontSize = 36.sp,
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = (-150).dp)
            )
            Column(
                modifier = Modifier.align(Alignment.BottomCenter)
                    .height(400.dp)
                    .width(350.dp)
                    .offset(y = (0).dp)
            )
            {
                Spacer(Modifier.size(10.dp))
                CustomColorButton(
                    onClick = {},
                    modifier = Modifier.height(70.dp)
                        .fillMaxWidth(),
                    primaryColor = Color(0xFF6db56c),
                    secondaryColor = Color(0xFF6db5fd)
                )
                {
                    Text(
                        text = "Message",
                        fontSize = 24.sp
                    )
                }
                Spacer(Modifier.size(40.dp))
                CustomColorButton(
                    onClick = {},
                    modifier = Modifier.height(70.dp)
                        .fillMaxWidth(),
                    primaryColor = Color(0xFFb087fd),
                    secondaryColor = Color(0xFFf98792)
                )
                {
                    Text(
                        text = "Add Friend",
                        fontSize = 24.sp
                    )
                }
                Spacer(Modifier.size(40.dp))
                CustomColorButton(
                    onClick = {},
                    modifier = Modifier.height(70.dp)
                        .fillMaxWidth(),
                    primaryColor = Color(0xFFe5523a),
                    secondaryColor = Color(0xFF99053d)
                )
                {
                    Text(
                        text = "Delete Contact",
                        fontSize = 24.sp
                    )
                }
                Spacer(Modifier.size(40.dp))
            }
        }
    }
}