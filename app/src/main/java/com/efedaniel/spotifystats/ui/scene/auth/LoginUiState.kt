package com.efedaniel.spotifystats.ui.scene.auth

import androidx.compose.runtime.Immutable
import com.efedaniel.spotifystats.core.Event

@Immutable
data class LoginUiState(
    val isConnecting: Boolean = false,
    val error: Event<String>? = null,
    val destination: Event<LoginDestination>? = null,
)
