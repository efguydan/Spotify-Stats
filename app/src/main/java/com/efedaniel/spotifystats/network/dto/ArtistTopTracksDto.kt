package com.efedaniel.spotifystats.network.dto

import com.google.gson.annotations.SerializedName

data class ArtistTopTracksDto(
    @SerializedName("tracks")
    val track: List<ArtistTrackDto>,
)

