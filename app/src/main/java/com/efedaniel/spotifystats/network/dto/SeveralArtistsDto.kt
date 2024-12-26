package com.efedaniel.spotifystats.network.dto

import com.google.gson.annotations.SerializedName

data class SeveralArtistsDto(
    @SerializedName("artists")
    val artists : List<ArtistDto>
)
