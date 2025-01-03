package com.efedaniel.spotifystats.ui.scene.artist

import androidx.compose.runtime.Immutable
import com.efedaniel.spotifystats.core.ScreenState
import com.efedaniel.spotifystats.domain.model.Album
import com.efedaniel.spotifystats.domain.model.Artist
import com.efedaniel.spotifystats.domain.model.ArtistTrack

@Immutable
data class ArtistUiState(
    val screenState: ScreenState = ScreenState.SUCCESS,
    val artist: Artist = Artist(),
    val album: List<Album> = emptyList(),
    val tracks: List<ArtistTrack> = emptyList(),
)
