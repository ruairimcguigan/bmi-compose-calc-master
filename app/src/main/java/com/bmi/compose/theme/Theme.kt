package com.bmi.compose.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
    primary = backgroundColor,
    primaryVariant = foregroundColor,
    onPrimary = foregroundColor,
    secondary = backgroundColor,
    secondaryVariant = backgroundColor,
    background = backgroundColor,
    onSecondary = foregroundColor,
    error = Red800
)

private val DarkThemeColors = darkColors(
    primary = Red300,
    primaryVariant = Red700,
    onPrimary = Color.Black,
    secondary = Red300,
    onSecondary = Color.White,
    error = Red200
)

@Composable
fun AppTheme(darkTheme: Boolean = false, content: @Composable() () -> Unit) {
    // dark theme disabled
    // val darkTheme = isSystemInDarkTheme()
    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = themeTypography,
        shapes = shapes,
        content = content
    )
}