package com.efedaniel.spotifystats.ui.scene.profile

import androidx.compose.runtime.Immutable

@Immutable
sealed class ProfileEvent {

    @Immutable
    object Logout: ProfileEvent()
}