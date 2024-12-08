package com.github.mkikets99.lntumessengerproject.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = BlueDark,
    secondary = BlueDarkSecondary,
    tertiary = BlueDarkTrinary
)

private val LightColorScheme = lightColorScheme(
    primary = Blue,
    secondary = BlueSecondary,
    tertiary = BlueTrinary

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun LNTUMessengerProjectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    WindowInsets.safeDrawing
    //val colorScheme = when {
    //    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
    //        val context = LocalContext.current
    //        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    //    }
//
    //    darkTheme -> DarkColorScheme
    //    else -> LightColorScheme
    //}

    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val typography = if (darkTheme) DarkTypography else LightTypography

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}