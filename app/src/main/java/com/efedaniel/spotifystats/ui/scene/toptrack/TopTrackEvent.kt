package com.efedaniel.spotifystats.ui.scene.toptrack

import androidx.compose.runtime.Immutable
import com.efedaniel.spotifystats.domain.model.TimeRange

@Immutable
sealed class TopTrackEvent {

    @Immutable
    data class TimeRangeChange(val timeRange: TimeRange): TopTrackEvent()

    @Immutable
    data class TrackClick(val id: String): TopTrackEvent()
}
