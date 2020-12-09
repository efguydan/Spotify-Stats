package com.efedaniel.spotifystats.ui.commons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.VerticalGradient
import com.efedaniel.spotifystats.ui.theme.spotifyBlack

// TODO Confirm if these colors work for both light and dark theme

fun getScrim(
    colors: List<Color> = listOf(spotifyBlack.copy(alpha = 0.4f), Color.Transparent),
    startY: Float = 0f,
    endY: Float = 100f
): LinearGradient {
    return VerticalGradient(
        colors = colors,
        startY = startY,
        endY = endY
    )
}
