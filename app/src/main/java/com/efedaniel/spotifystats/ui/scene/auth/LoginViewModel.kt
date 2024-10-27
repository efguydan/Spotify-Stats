package com.efedaniel.spotifystats.ui.scene.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.efedaniel.spotifystats.core.BaseViewModel
import com.efedaniel.spotifystats.core.Event
import com.efedaniel.spotifystats.domain.manager.AuthDomainManager
import com.efedaniel.spotifystats.domain.manager.UserDomainManager
import com.efedaniel.spotifystats.ui.scene.auth.LoginDestination.MAIN
import com.efedaniel.spotifystats.utility.constants.Constants.DEFAULT_ERROR_MESSAGE
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authDomainManager: AuthDomainManager,
    private val userDomainManager: UserDomainManager,
): BaseViewModel() {

    var state by mutableStateOf(LoginUiState())
        private set

    fun onConnectSpotifyResponse(response: AuthorizationResponse) {
        Timber.e(response.type.toString())
        Timber.e(response.accessToken.toString())
        when (response.type) {
            AuthorizationResponse.Type.TOKEN -> {
                onConnectSpotifySuccess(
                    accessToken = response.accessToken,
                )
            }

            AuthorizationResponse.Type.ERROR -> {
                onConnectSpotifyError(
                    errorMessage = response.error
                )
            }

            else -> {
                Timber.e("Spotify Login Failed for unknown reasons")
                onConnectSpotifyError(
                    errorMessage = DEFAULT_ERROR_MESSAGE
                )
            }
        }
    }

    private fun onConnectSpotifySuccess(accessToken: String) {
        authDomainManager
            .storeAccessToken(accessToken = accessToken)
            .andThen(userDomainManager.fetchCurrentUser())
            .doOnSubscribe { state = state.copy(isConnecting = true) }
            .subscribeBy(
                onSuccess = {
                    Timber.e(it.toString())
                    state = state.copy(
                        isConnecting = false,
                        destination = Event(MAIN)
                    )
                },
                onError = {
                    Timber.e(it)
                    state = state.copy(
                        isConnecting = false,
                        error = Event(it.message ?: DEFAULT_ERROR_MESSAGE)
                    )
                },
            )
            .addTo(disposables)


    }

    private fun onConnectSpotifyError(errorMessage: String) {
        Timber.e(errorMessage)
        state = state.copy(
            isConnecting = false,
            error = Event(errorMessage)
        )
    }
}
