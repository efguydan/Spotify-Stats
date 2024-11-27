package com.efedaniel.spotifystats.network.dto

import com.google.gson.annotations.SerializedName

data class ArtistRelatedArtistDto(
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
    @SerializedName("followers")
    val followers: FollowersDto,
    @SerializedName("type")
    val type: String,
    @SerializedName("uri")
    val uri: String
)
