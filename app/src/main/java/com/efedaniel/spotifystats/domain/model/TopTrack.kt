package com.efedaniel.spotifystats.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class TopTrack(
    val id: String,
    val position: Int,
    val name: String,
    val imageUrl: String?,
    val isExplicit: Boolean,
    val popularity: Int,
    val previewUrl: String,
    val uri: String,
)
