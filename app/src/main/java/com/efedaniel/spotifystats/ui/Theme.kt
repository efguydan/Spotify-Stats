package com.efedaniel.spotifystats.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = spotifyBlack,
    primaryVariant = Color.Black,
    secondary = spotifyLime,
    background = spotifyBlack,
    surface = spotifyBlack,
    //error,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onError = Color.White
)

private val LightColorPalette = lightColors(

        /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

// TODO Change to isSystemInDarkTheme() later; when ready to implement light theme
@Composable
fun SpotifyStatsTheme(darkTheme: Boolean = true, content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
