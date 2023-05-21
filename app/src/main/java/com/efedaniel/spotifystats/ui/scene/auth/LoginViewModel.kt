package com.efedaniel.spotifystats.ui.scene.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.efedaniel.spotifystats.core.event.Event
import com.efedaniel.spotifystats.domain.manager.AuthDomainManager
import com.efedaniel.spotifystats.ui.scene.auth.state.LoginUiState
import com.efedaniel.spotifystats.utility.constants.Constants.DEFAULT_ERROR_MESSAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val domain: AuthDomainManager,
): ViewModel() {

    private val disposables = CompositeDisposable()

    var state by mutableStateOf(LoginUiState())
        private set

    fun onConnectSpotifyResult(code: String?, error: String?) {
        if (code != null) {
            authenticateUser(code)
        } else {
            state = state.copy(
                error = Event(error ?: DEFAULT_ERROR_MESSAGE)
            )
        }
    }

    private fun authenticateUser(code: String) {
        domain
            .authenticateUser(code)
            .doOnSubscribe { state = state.copy(isConnecting = true) }
            .subscribe(
                {
                    state = state.copy(isConnecting = false)
                    Timber.e(it.toString())
                },
                { Timber.e(it.toString()) }
            )
            .addTo(disposables)
    }
}
