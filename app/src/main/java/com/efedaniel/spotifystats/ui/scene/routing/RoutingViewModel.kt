package com.efedaniel.spotifystats.ui.scene.routing

import androidx.lifecycle.ViewModel
import com.efedaniel.spotifystats.domain.manager.AuthDomainManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoutingViewModel @Inject constructor(
    private val authDomainManager: AuthDomainManager
) : ViewModel() {

    fun isUserAuthenticated(): Boolean =
        authDomainManager
            .isUserAuthenticated()
}