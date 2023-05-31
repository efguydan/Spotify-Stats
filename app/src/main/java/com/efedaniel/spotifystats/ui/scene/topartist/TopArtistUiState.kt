package com.efedaniel.spotifystats.ui.scene.topartist

import androidx.compose.runtime.Immutable
import com.efedaniel.spotifystats.domain.model.TopArtist

@Immutable
data class TopArtistUiState(
    val artists: List<TopArtist> = emptyList(), // Fixme: Change to ImmutableList
)
