package com.efedaniel.spotifystats.ui.scene.toptrack

import androidx.compose.runtime.Immutable
import com.efedaniel.spotifystats.core.ScreenState
import com.efedaniel.spotifystats.domain.model.TimeRange
import com.efedaniel.spotifystats.domain.model.TopTrack

@Immutable
data class TopTrackUiState(
    val screenState: ScreenState = ScreenState.SUCCESS,
    val tracks: List<TopTrack> = emptyList(), // Fixme: Change to ImmutableList
    val timeRange: TimeRange = TimeRange.SHORT_TERM
)
