package com.efedaniel.spotifystats.domain.model

import androidx.compose.runtime.Immutable

@Immutable
enum class TimeRange(val value: String) {
    SHORT_TERM("short_term"),
    MEDIUM_TERM("medium_term"),
    LONG_TERM("long_term"),
}