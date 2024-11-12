package com.efedaniel.spotifystats.ui.scene.overview

import com.efedaniel.spotifystats.core.BaseViewModel
import com.efedaniel.spotifystats.domain.manager.PlaybackDomainManager
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val playbackDomainManager: PlaybackDomainManager,
): BaseViewModel() {

    fun fetchCurrentPlayback() {
        playbackDomainManager
            .getPlaybackState()
            .subscribeBy(
                onSuccess = {
                    Timber.e("The Playbck was fetched")
                    Timber.e(it.toString())
                },
                onError = {
                    Timber.e("There was an error")
                    Timber.e(it.toString())
                },
            )
            .addTo(disposables)
    }
}