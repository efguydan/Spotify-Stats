package com.efedaniel.spotifystats.ui.scene.topartist

import androidx.compose.runtime.Immutable
import com.efedaniel.spotifystats.core.ScreenState
import com.efedaniel.spotifystats.domain.model.TimeRange
import com.efedaniel.spotifystats.domain.model.TopArtist

@Immutable
data class TopArtistUiState(
    val screenState: ScreenState = ScreenState.SUCCESS,
    val artists: List<TopArtist> = emptyList(), // Fixme: Change to ImmutableList
    val timeRange: TimeRange = TimeRange.SHORT_TERM
)
