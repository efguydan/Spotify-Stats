package com.efedaniel.spotifystats.ui.scene.topartist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.efedaniel.spotifystats.core.BaseViewModel
import com.efedaniel.spotifystats.core.ScreenState
import com.efedaniel.spotifystats.domain.manager.StatsDomainManager
import com.efedaniel.spotifystats.domain.model.TimeRange
import com.efedaniel.spotifystats.utility.constants.Constants.STATS_LIMIT
import com.efedaniel.spotifystats.utility.constants.Constants.STATS_OFFSET
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TopArtistViewModel @Inject constructor(
    private val statsDomainManager: StatsDomainManager,
    val navigator: TopArtistNavigator,
): BaseViewModel() {

    var state by mutableStateOf(TopArtistUiState())
        private set

    val destinations: MutableSharedFlow<TopArtistDestination> = MutableSharedFlow()

    init {
        fetchTopArtists()
    }

    private fun fetchTopArtists() {
        statsDomainManager
            .getTopArtists(
                timeRange = state.timeRange,
                limit = STATS_LIMIT,
                offset = STATS_OFFSET,
            )
            .doOnSubscribe { state = state.copy(screenState = ScreenState.LOADING) }
            .subscribeBy(
                onSuccess = {
                    state = state.copy(
                        screenState = ScreenState.SUCCESS,
                        artists = it,
                    )
                },
                onError = {
                    state = TopArtistUiState(screenState = ScreenState.ERROR)
                    Timber.e("There was an error")
                    Timber.e(it)
                }
            )
            .addTo(disposables)
    }

    fun onNewEvent(event: TopArtistEvent) {
        when (event) {
            is TopArtistEvent.TimeRangeChange -> onTimeRangeSelected(event.timeRange)
            is TopArtistEvent.ArtistClick -> viewModelScope.launch {
                destinations.emit(TopArtistDestination.Artist(event.id))
            }
        }
    }

    private fun onTimeRangeSelected(timeRange: TimeRange) {
        if (state.timeRange != timeRange) {
            state = state.copy(timeRange = timeRange)
            fetchTopArtists()
        }
    }
}