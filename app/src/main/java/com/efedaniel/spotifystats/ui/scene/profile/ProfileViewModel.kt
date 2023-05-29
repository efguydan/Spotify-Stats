package com.efedaniel.spotifystats.ui.scene.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.efedaniel.spotifystats.core.BaseViewModel
import com.efedaniel.spotifystats.core.Event
import com.efedaniel.spotifystats.domain.manager.AuthDomainManager
import com.efedaniel.spotifystats.domain.manager.UserDomainManager
import com.efedaniel.spotifystats.ui.scene.profile.ProfileDestination.LOGIN
import com.efedaniel.spotifystats.ui.scene.profile.ProfileEvent.Logout
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userDomainManager: UserDomainManager,
    private val authDomainManager: AuthDomainManager,
    val navigator: ProfileNavigator,
): BaseViewModel() {

    var state by mutableStateOf(ProfileUiState())
        private set

    init {
        retrieveCurrentUser()
    }

    private fun retrieveCurrentUser() {
        userDomainManager
            .retrieveCurrentUserFromCache()
            .subscribeBy(
                onSuccess = { user ->
                    Timber.e(user.toString())
                    state = state.copy(user = user)
                },
                onError = {
                    Timber.e(it.toString())
                }
            )
            .addTo(disposables)
    }

    fun onNewEvent(event: ProfileEvent) {
        when (event) {
            Logout -> logoutUser()
        }
    }

    private fun logoutUser() {
        authDomainManager.logout()
        state = state.copy(destination = Event(LOGIN))
    }
}