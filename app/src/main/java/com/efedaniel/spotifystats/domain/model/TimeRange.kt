package com.efedaniel.spotifystats.domain.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.efedaniel.spotifystats.R

@Immutable
enum class TimeRange(
    @StringRes val title: Int,
    val key: String
) {
    SHORT_TERM(R.string.four_weeks, "short_term"),
    MEDIUM_TERM(R.string.six_months,"medium_term"),
    LONG_TERM(R.string.all_time, "long_term"),
}