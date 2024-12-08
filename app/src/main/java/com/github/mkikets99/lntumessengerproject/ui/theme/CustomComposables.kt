package com.github.mkikets99.lntumessengerproject.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.unit.dp

@Composable
fun CustomColorButton(
    onClick: () -> Unit,
    modifier: Modifier,
    shape: Shape,
    primaryColor: Color,
    secondaryColor: Color,
    contentPadding: PaddingValues,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        contentPadding = contentPadding,
        modifier = modifier,
        shape = shape,
        colors = ButtonColors(
            containerColor = primaryColor,
            contentColor = secondaryColor,
            disabledContainerColor = lerp(primaryColor, Color.Black, 0.8f),
            disabledContentColor = lerp(secondaryColor, Color.Black, 0.8f),
        )
    )
    {
        content()
    }
}

@Composable
fun CustomColorButton(
    onClick: () -> Unit,
    modifier: Modifier,
    primaryColor: Color,
    secondaryColor: Color,
    contentPadding: PaddingValues,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        contentPadding = contentPadding,
        colors = ButtonColors(
            containerColor = primaryColor,
            contentColor = secondaryColor,
            disabledContainerColor = lerp(primaryColor, Color.Black, 0.8f),
            disabledContentColor = lerp(secondaryColor, Color.Black, 0.8f),
        )
    )
    {
        content()
    }
}

@Composable
fun CustomButton(
    onClick: () -> Unit,
    modifier: Modifier,
    shape: Shape,
    contentPadding: PaddingValues,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        contentPadding = contentPadding,
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.secondary,
            disabledContainerColor = lerp(MaterialTheme.colorScheme.primary, Color.Black, 0.8f),
            disabledContentColor = lerp(MaterialTheme.colorScheme.secondary, Color.Black, 0.8f),
        )
    )
    {
        content()
    }
}

@Composable
fun CustomButton(
    onClick: () -> Unit,
    modifier: Modifier,
    contentPadding: PaddingValues,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        contentPadding = contentPadding,
        modifier = modifier,
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.secondary,
            disabledContainerColor = lerp(MaterialTheme.colorScheme.primary, Color.Black, 0.8f),
            disabledContentColor = lerp(MaterialTheme.colorScheme.secondary, Color.Black, 0.8f),
        )
    )
    {
        content()
    }
}

@Composable
fun CustomButton(
    onClick: () -> Unit,
    contentPadding: PaddingValues,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        contentPadding = contentPadding,
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.secondary,
            disabledContainerColor = lerp(MaterialTheme.colorScheme.primary, Color.Black, 0.8f),
            disabledContentColor = lerp(MaterialTheme.colorScheme.secondary, Color.Black, 0.8f),
        )
    )
    {
        content()
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    shape: Shape
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        shape = shape,
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        modifier = modifier.fillMaxSize(),
        shape = RoundedCornerShape(32.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        shape = RoundedCornerShape(32.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}