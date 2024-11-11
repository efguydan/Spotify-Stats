package com.efedaniel.spotifystats.domain.model

data class Album(
    val id: String = "",
    val imageUrl: String? = null,
    val name: String = "",
    val artists: List<String> = listOf(""),
    val spotifyUrl: String = "",
    val uri : String= "",
    )
