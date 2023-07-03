package com.efedaniel.spotifystats.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class Artist(
    val id: String,
    val spotifyUrl: String,
    val genres: List<String>,  // Fixme: Change to ImmutableList
    val imageUrl: String?,
    val name: String,
    val popularity: Int,
)