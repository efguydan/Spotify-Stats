package com.efedaniel.spotifystats.ui.scene.topartist

import androidx.navigation.NavHostController
import com.efedaniel.spotifystats.ui.scene.main.MainDestination.Artist
import javax.inject.Inject

class TopArtistNavigator @Inject constructor() {

    fun navigate(
        destination: TopArtistDestination,
        navController: NavHostController
    ) {
        when (destination) {
            is TopArtistDestination.Artist -> navigateToArtist(destination.id, navController)
        }
    }

    private fun navigateToArtist(
        id: String,
        navController: NavHostController
    ) {
        navController.navigate(Artist.getRoute(id = id))
    }
}