package com.efedaniel.spotifystats.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class Album(
    val id: String,
    val imageUrl: String?,
    val name: String,
    val artists: List<String>?,
    val spotifyUrl: String,
    val uri: String?,
)
