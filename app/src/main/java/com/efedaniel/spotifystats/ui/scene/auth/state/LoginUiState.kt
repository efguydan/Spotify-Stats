package com.efedaniel.spotifystats.ui.scene.auth.state

import androidx.compose.runtime.Immutable

@Immutable
data class LoginUiState(
    val isConnecting: Boolean = false,
)
