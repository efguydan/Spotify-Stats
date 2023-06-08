package com.efedaniel.spotifystats.ui.scene.topartist

import androidx.compose.runtime.Immutable
import com.efedaniel.spotifystats.domain.model.TimeRange

@Immutable
sealed class TopArtistEvent {

    @Immutable
    data class TimeRangeChange(val timeRange: TimeRange): TopArtistEvent()

    @Immutable
    data class ArtistClick(val id: String): TopArtistEvent()
}