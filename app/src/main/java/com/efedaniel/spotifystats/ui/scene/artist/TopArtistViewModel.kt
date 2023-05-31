package com.efedaniel.spotifystats.ui.scene.artist

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

    fun fetchTopArtists(timeRange: TimeRange) {
        statsDomainManager
            .getTopArtists(
                timeRange = timeRange,
                limit = STATS_LIMIT,
                offset = STATS_OFFSET,
            )
            .subscribeBy(
                onSuccess = {
                    Timber.e("Artists have been loaded")
                    Timber.e(it.toString())
                },
                onError = {
                    Timber.e("There was an error")
                    Timber.e(it)
                }
            )
            .addTo(disposables)
    }
}