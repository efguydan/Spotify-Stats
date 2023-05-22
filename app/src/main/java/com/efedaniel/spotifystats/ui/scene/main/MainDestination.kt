package com.efedaniel.spotifystats.ui.scene.main

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.efedaniel.spotifystats.R
import com.efedaniel.spotifystats.ui.proton.tokens.icon.ProtonIconAsset

@Immutable
sealed class MainDestination(
    val route: String,
    @StringRes val titleRes: Int,
    val icon: ProtonIconAsset,
) {

    @Immutable
    object Overview: MainDestination(
        route = "overview",
        titleRes = R.string.overview,
        icon = ProtonIconAsset.Spotify
    )

    @Immutable
    object Music: MainDestination(
        route = "music",
        titleRes = R.string.music,
        icon = ProtonIconAsset.Spotify
    )

    @Immutable
    object Artist: MainDestination(
        route = "artist",
        titleRes = R.string.artist,
        icon = ProtonIconAsset.Spotify
    )

    @Immutable
    object Profile: MainDestination(
        route = "profile",
        titleRes = R.string.profile,
        icon = ProtonIconAsset.Spotify
    )
}

val MainDestinations = listOf(
    MainDestination.Overview,
    MainDestination.Music,
    MainDestination.Artist,
    MainDestination.Profile
)
