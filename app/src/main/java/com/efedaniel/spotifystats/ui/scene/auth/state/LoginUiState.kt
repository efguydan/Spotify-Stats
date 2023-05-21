package com.efedaniel.spotifystats.ui.scene.auth.state

import androidx.compose.runtime.Immutable
import com.efedaniel.spotifystats.core.event.Event

@Immutable
data class LoginUiState(
    val isConnecting: Boolean = false,
    val error: Event<String>? = null
)
