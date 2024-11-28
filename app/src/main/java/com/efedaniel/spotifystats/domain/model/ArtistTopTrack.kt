package com.efedaniel.spotifystats.domain.model

data class ArtistTopTrack(
    val id: String,
    val name: String,
    val imageUrl: String?,
    val isExplicit: Boolean,
    val popularity: Int,
    val previewUrl: String?,
    val uri: String,
)
