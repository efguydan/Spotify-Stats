package com.efedaniel.spotifystats.ui.proton.tokens.icon

import androidx.compose.runtime.Stable
import com.efedaniel.spotifystats.R

@Stable
enum class ProtonIconAsset(
    val iconRes: Int,
    val contentDescription: String,
) {

    Spotify(
        iconRes = R.drawable.icons8_spotify,
        contentDescription = "Spotify"
    )
}