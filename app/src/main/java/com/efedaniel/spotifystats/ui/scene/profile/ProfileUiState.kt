package com.efedaniel.spotifystats.ui.scene.profile

import androidx.compose.runtime.Immutable
import com.efedaniel.spotifystats.domain.model.User

@Immutable
data class ProfileUiState(
    val user: User = User()
)
