package com.efedaniel.spotifystats.ui.scene.artist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.efedaniel.spotifystats.core.BaseViewModel
import com.efedaniel.spotifystats.core.ScreenState.ERROR
import com.efedaniel.spotifystats.core.ScreenState.LOADING
import com.efedaniel.spotifystats.core.ScreenState.SUCCESS
import com.efedaniel.spotifystats.domain.manager.ArtistDomainManager
import com.efedaniel.spotifystats.domain.manager.UserDomainManager
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(
    private val userDomainManager: UserDomainManager,
    private val artistDomainManager: ArtistDomainManager,
): BaseViewModel() {

    var state by mutableStateOf(ArtistUiState(screenState = LOADING))
        private set

    fun fetchArtist(id: String?) {
        artistDomainManager
            .getArtist(id.orEmpty())
            .doOnSubscribe { state = state.copy(screenState = LOADING) }
            .subscribeBy(
                onSuccess = {
                    state = ArtistUiState(
                        screenState = SUCCESS,
                        artist = it,
                    )
                },
                onError = {
                    state = ArtistUiState(screenState = ERROR,)
                    Timber.e("There was an error")
                    Timber.e(it)
                }
            )
            .addTo(disposables)
    }

    fun fetchArtistAlbum(id: String) {
        artistDomainManager.getArtistAlbum(id)
            .doOnSubscribe { state = state.copy(screenState = LOADING) }
            .subscribe({ albumList ->
                state = state.copy(
                    screenState = SUCCESS,
                    album = albumList.first()

                )
                Timber.d(albumList.toString())

            }, { error ->
                state = state.copy(screenState = ERROR,)
                Timber.e("There was an error")
                Timber.e(error)

            })
            .let { disposables.add(it) }
    }

    fun fetchArtistTopTracks(id: String?) {
        artistDomainManager
            .getArtistTopTracks(id.orEmpty())
            .doOnSubscribe { state = state.copy(screenState = LOADING) }
            .subscribeBy (
                onSuccess = { topTracks ->
                    state = state.copy(
                        screenState = SUCCESS,
                        tracks = topTracks )
                    Timber.d(topTracks.toString())
                },
                onError = {
                    state = state.copy(screenState = ERROR,)
                    Timber.e("There was an error")
                    Timber.e(it)
                }
            )
            .let { disposables.add(it) }  // Add the disposable to the CompositeDisposable
    }

}