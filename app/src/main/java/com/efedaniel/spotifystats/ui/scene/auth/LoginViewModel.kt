package com.efedaniel.spotifystats.ui.scene.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.efedaniel.spotifystats.ui.scene.auth.state.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigator: LoginNavigator,
): ViewModel() {

    var state by mutableStateOf(LoginUiState())
        private set

    fun onConnectSpotifyClicked() {
        navigator.navigateToSpotifyWebLogin()
    }
}