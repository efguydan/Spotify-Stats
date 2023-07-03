package com.efedaniel.spotifystats.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class Artist(
    val id: String = "",
    val spotifyUrl: String = "",
    val genres: List<String> = emptyList(),  // Fixme: Change to ImmutableList
    val imageUrl: String? = null,
    val name: String = "",
    val popularity: Int = 0,
)