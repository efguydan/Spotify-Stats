package com.efedaniel.spotifystats.ui.commons

import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.VerticalGradient
import com.efedaniel.spotifystats.ui.theme.spotifyBlack

// TODO Confirm if these colors work for both light and dark theme
val scrim: LinearGradient get() = VerticalGradient(
    colors = listOf(
        spotifyBlack.copy(alpha = 0.2f),
        spotifyBlack.copy(alpha = 0.0f)
    ),
    startY = 100f,
    endY = 0f
)
