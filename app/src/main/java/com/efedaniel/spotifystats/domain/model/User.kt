package com.efedaniel.spotifystats.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class User(
    val id: String = "",
    val countryIso: String = "",
    val displayName: String = "",
    val followers: Int = 0,
    val imageUrl: String? = null,
    val spotifyUri: String = "",
)
