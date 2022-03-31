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
    primary = Greenish,
    primaryVariant = Purple700,
    secondary = Reddish,
    secondaryVariant = Teal700,
    background = BackgroundLight,
    surface = SurfaceLight,
    error = ErrorLight,
    onPrimary = Color.White,
    onSecondary = SurfaceLight,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White
)

private val DarkColorPalette = darkColors(
    primary = LightGreenish,
    primaryVariant = Purple700,
    secondary = Orangish,
    secondaryVariant = Orangish,
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
        systemUiController.setStatusBarColor(color = BackgroundDark)
        systemUiController.setNavigationBarColor(color = SurfaceDark)
        DarkColorPalette
    } else {
        systemUiController.setStatusBarColor(color = BackgroundLight)
        systemUiController.setNavigationBarColor(color = SurfaceLight)
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}