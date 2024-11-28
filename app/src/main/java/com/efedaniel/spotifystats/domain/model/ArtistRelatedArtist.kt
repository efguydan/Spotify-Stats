package com.efedaniel.spotifystats.domain.model

data class ArtistRelatedArtist(
    val id: String = "",
    val genres: List<String> = emptyList(),  // Fixme: Change to ImmutableList
    val imageUrl: String? = null,
    val name: String = "",
    val popularity: Int = 0,

)
