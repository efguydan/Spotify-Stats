package com.efedaniel.spotifystats.ui.screens.tracks

import com.efedaniel.spotifystats.base.mvi.MviResult
import com.efedaniel.spotifystats.model.general.Track

sealed class TracksResult : MviResult {

    sealed class LoadTracksResult : TracksResult() {
        data class Success(val tracks: List<Track>) : LoadTracksResult()
        data class Failure(val error: Throwable) : LoadTracksResult()
        object InFlight : LoadTracksResult()
    }

    sealed class SelectTracksResult : TracksResult() {
        data class Success(val track: Track) : SelectTracksResult()
        data class Failure(val error: Throwable) : SelectTracksResult()
        object InFlight : SelectTracksResult()
    }
}
