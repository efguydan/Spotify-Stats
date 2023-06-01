package com.efedaniel.spotifystats.ui.scene.topartist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.efedaniel.spotifystats.core.BaseViewModel
import com.efedaniel.spotifystats.domain.manager.StatsDomainManager
import com.efedaniel.spotifystats.domain.model.TimeRange
import com.efedaniel.spotifystats.utility.constants.Constants.STATS_LIMIT
import com.efedaniel.spotifystats.utility.constants.Constants.STATS_OFFSET
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TopArtistViewModel @Inject constructor(
    private val statsDomainManager: StatsDomainManager,
): BaseViewModel() {

    var state by mutableStateOf(TopArtistUiState())
        private set

    fun fetchTopArtists() {
        statsDomainManager
            .getTopArtists(
                timeRange = state.timeRange,
                limit = STATS_LIMIT,
                offset = STATS_OFFSET,
            )
            .subscribeBy(
                onSuccess = { state = state.copy(artists = it) },
                onError = {
                    Timber.e("There was an error")
                    Timber.e(it)
                }
            )
            .addTo(disposables)
    }

    fun onTimeRangeSelected(timeRange: TimeRange) {
        if (state.timeRange != timeRange) {
            state = state.copy(timeRange = timeRange)
            fetchTopArtists()
        }
    }
}