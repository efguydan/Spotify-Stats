package com.efedaniel.spotifystats.ui.screens.tracks

import com.efedaniel.spotifystats.base.mvi.MviViewState
import com.efedaniel.spotifystats.model.general.Track

// TODO Add Tasks Time Frame to this View State?
data class TracksViewState(
    val isLoading: Boolean,
    val tracks: List<Track>,
    val error: Throwable?,
) : MviViewState {

    companion object {
        fun idle(): TracksViewState = TracksViewState(
            isLoading = false,
            tracks = emptyList(),
            error = null,
        )
    }
}
