package com.efedaniel.spotifystats.core

import androidx.compose.runtime.Immutable

@Immutable
enum class ScreenState {
    LOADING,
    SUCCESS,
    ERROR,
}