package com.efedaniel.spotifystats.ui.scene.artist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.efedaniel.spotifystats.core.BaseViewModel
import com.efedaniel.spotifystats.core.ScreenState.ERROR
import com.efedaniel.spotifystats.core.ScreenState.LOADING
import com.efedaniel.spotifystats.core.ScreenState.SUCCESS
import com.efedaniel.spotifystats.domain.manager.UserDomainManager
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(
    private val userDomainManager: UserDomainManager,
): BaseViewModel() {

    var state by mutableStateOf(ArtistUiState(screenState = LOADING))
        private set

    fun fetchArtist(id: String?) {
        userDomainManager
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
}