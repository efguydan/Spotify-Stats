package com.efedaniel.spotifystats.ui.scene.topartist

import androidx.compose.runtime.Immutable

@Immutable
sealed class TopArtistDestination {

    @Immutable
    data class Artist(val id: String): TopArtistDestination()
}