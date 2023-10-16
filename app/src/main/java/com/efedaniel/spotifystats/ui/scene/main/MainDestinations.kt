package com.efedaniel.spotifystats.ui.scene.main

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
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
    object TopTrack: MainBottomDestination(
        route = "top_track",
        titleRes = R.string.tracks,
        icon = ProtonIconAsset.Spotify
    )

    @Immutable
    object TopArtist: MainBottomDestination(
        route = "top_artist",
        titleRes = R.string.artists,
        icon = ProtonIconAsset.Spotify
    )
}

@Immutable
sealed class MainDestination (
    override val route: String,
    val arguments: List<NamedNavArgument> = emptyList(),
) : Destination {

    @Immutable
    object Artist: MainDestination(
        route = "artist/{id}",
        arguments = listOf(navArgument("id") { type = NavType.StringType })
    ) {
        fun getRoute(id: String): String = route.replace("{id}", id)
    }
}

val BottomDestinations = listOf(
    MainBottomDestination.Overview,
    MainBottomDestination.TopTrack,
    MainBottomDestination.TopArtist,
    MainBottomDestination.Profile,
)
