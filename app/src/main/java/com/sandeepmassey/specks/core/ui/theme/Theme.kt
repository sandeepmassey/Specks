package com.sandeepmassey.specks.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    secondaryVariant = Teal700,
    background = BackgroundLight,
    surface = SurfaceLight,
    error = ErrorLight,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White
)

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
    secondaryVariant = Teal200,
    background = BackgroundDark,
    surface = SurfaceDark,
    error = ErrorDark,
    onPrimary = BackgroundDark,
    onSecondary = BackgroundDark,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = BackgroundDark
)

@Composable
fun SpecksTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val colors: Colors = if (darkTheme) {
        systemUiController.setSystemBarsColor(color = SurfaceDark)
        DarkColorPalette
    } else {
        systemUiController.setSystemBarsColor(color = SurfaceLight)
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}