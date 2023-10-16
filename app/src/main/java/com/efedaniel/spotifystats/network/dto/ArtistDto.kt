package com.efedaniel.spotifystats.network.dto

import com.google.gson.annotations.SerializedName

data class ArtistDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("images")
    val images: List<ImageDto>,
    @SerializedName("name")
    val name: String,
    @SerializedName("popularity")
    val popularity: Int,
)