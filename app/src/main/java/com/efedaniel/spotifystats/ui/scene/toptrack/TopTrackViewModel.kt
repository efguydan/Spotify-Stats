package com.efedaniel.spotifystats.ui.scene.toptrack

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.efedaniel.spotifystats.core.BaseViewModel
import com.efedaniel.spotifystats.core.ScreenState
import com.efedaniel.spotifystats.domain.manager.StatsDomainManager
import com.efedaniel.spotifystats.domain.model.TimeRange
import com.efedaniel.spotifystats.ui.scene.toptrack.TopTrackEvent.TimeRangeChange
import com.efedaniel.spotifystats.ui.scene.toptrack.TopTrackEvent.TrackClick
import com.efedaniel.spotifystats.utility.constants.Constants.STATS_LIMIT
import com.efedaniel.spotifystats.utility.constants.Constants.STATS_OFFSET
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TopTrackViewModel @Inject constructor(
    private val statsDomainManager: StatsDomainManager,
): BaseViewModel() {

    var state by mutableStateOf(TopTrackUiState())
        private set

    init {
        fetchTopTracks()
    }

    private fun fetchTopTracks() {
        statsDomainManager
            .getTopTracks(
                timeRange = state.timeRange,
                limit = STATS_LIMIT,
                offset = STATS_OFFSET,
            )
            .doOnSubscribe { state = state.copy(screenState = ScreenState.LOADING) }
            .subscribeBy(
                onSuccess = {
                    state = state.copy(
                        screenState = ScreenState.SUCCESS,
                        tracks = it,
                    )
                },
                onError = {
                    state = TopTrackUiState(screenState = ScreenState.ERROR)
                    Timber.e("There was an error")
                    Timber.e(it)
                }
            )
            .addTo(disposables)
    }

    fun onNewEvent(event: TopTrackEvent) {
        when(event) {
            is TimeRangeChange -> onTimeRangeSelected(event.timeRange)
            is TrackClick -> {
                // TODO Handle Click
            }
        }
    }

    private fun onTimeRangeSelected(timeRange: TimeRange) {
        if (state.timeRange != timeRange) {
            state = state.copy(timeRange = timeRange)
            fetchTopTracks()
        }
    }
}