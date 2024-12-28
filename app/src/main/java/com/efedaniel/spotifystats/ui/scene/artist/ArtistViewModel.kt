package com.efedaniel.spotifystats.ui.scene.artist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.efedaniel.spotifystats.core.BaseViewModel
import com.efedaniel.spotifystats.core.ScreenState.ERROR
import com.efedaniel.spotifystats.core.ScreenState.LOADING
import com.efedaniel.spotifystats.core.ScreenState.SUCCESS
import com.efedaniel.spotifystats.domain.manager.ArtistDomainManager
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(
    private val artistDomainManager: ArtistDomainManager,
): BaseViewModel() {

    var state by mutableStateOf(ArtistUiState(screenState = LOADING))
        private set

    fun fetchArtistInformation(id: String?) {
        id ?: kotlin.run {
            state = ArtistUiState(screenState = ERROR)
            Timber.e("The ID should not be null")
            return
        }

//        fetchArtist(id)
//        fetchArtistAlbums(id)
        fetchArtistTopTracks(id)
    }

    fun fetchArtist(id: String) {
        artistDomainManager
            .getArtist(id)
            .doOnSubscribe { state = state.copy(screenState = LOADING) }
            .subscribeBy(
                onSuccess = {
                    state = ArtistUiState(
                        screenState = SUCCESS,
                        artist = it,
                    )
                },
                onError = {
                    state = ArtistUiState(screenState = ERROR)
                    Timber.e("There was an error")
                    Timber.e(it)
                }
            )
            .addTo(disposables)
    }

    fun fetchArtistAlbums(id: String) {
        artistDomainManager.getArtistAlbum(id)
            .doOnSubscribe { state = state.copy(screenState = LOADING) }
            .subscribe({ albumList ->
                state = state.copy(
                    screenState = SUCCESS,
                    album = albumList
                )
                Timber.d(albumList.toString())

            }, { error ->
                state = state.copy(screenState = ERROR,)
                Timber.e("There was an error")
                Timber.e(error)

            })
            .addTo(disposables)
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
            .addTo(disposables)
    }

    // TODO @ufuoma remove this
    fun fetchSeveralArtists(id: List<String>) {
        artistDomainManager
            .getSeveralArtists(id)
            .doOnSubscribe { state = state.copy(screenState = LOADING) }
            .subscribeBy(
                onSuccess = { artists ->
                    state = state.copy(
                        screenState = SUCCESS,
                        severalArtist = artists
                    )
                    Timber.d(artists.toString())
                },
                onError = {
                    state = state.copy(screenState = ERROR,)
                    Timber.e("There was an error")
                    Timber.e(it)
                }
            )
            .addTo(disposables)
    }
}