package com.efedaniel.spotifystats.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class TopArtist(
    val id: String,
    val position: Int,
    val name: String,
    val imageUrl: String?,
    val popularity: Int,
    val genres: List<String>, // Fixme: Change to Immutable List
    val spotifyUrl: String,
    val uri: String,
)