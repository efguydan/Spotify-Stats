package com.efedaniel.spotifystats.ui.scene.main

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.efedaniel.spotifystats.R
import com.efedaniel.spotifystats.ui.proton.tokens.icon.ProtonIconAsset

interface Destination {
    val route: String
}

@Immutable
sealed class MainBottomDestination(
    override val route: String,
    @StringRes val titleRes: Int,
    val icon: ProtonIconAsset,
): Destination {

    @Immutable
    object Overview: MainBottomDestination(
        route = "overview",
        titleRes = R.string.overview,
        icon = ProtonIconAsset.Spotify
    )

    @Immutable
    object Profile: MainBottomDestination(
        route = "profile",
        titleRes = R.string.profile,
        icon = ProtonIconAsset.Spotify
    )

    @Immutable
    object TopMusic: MainBottomDestination(
        route = "topmusic",
        titleRes = R.string.music,
        icon = ProtonIconAsset.Spotify
    )

    @Immutable
    object TopArtist: MainBottomDestination(
        route = "topartist",
        titleRes = R.string.artist,
        icon = ProtonIconAsset.Spotify
    )
}

@Immutable
sealed class MainDestination (
    override val route: String
) : Destination {

    @Immutable
    object Artist: MainDestination(
        route = "artist"
    )
}

val AllDestinations = listOf(
    MainBottomDestination.Overview,
    MainBottomDestination.TopMusic,
    MainBottomDestination.TopArtist,
    MainBottomDestination.Profile,
)
