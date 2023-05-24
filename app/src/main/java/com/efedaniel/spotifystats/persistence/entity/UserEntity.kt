package com.efedaniel.spotifystats.persistence.entity

data class UserEntity(
    val id: String,
    val countryIso: String,
    val displayName: String,
    val followers: Int,
    val imageUrl: String?,
    val spotifyUri: String,
)
