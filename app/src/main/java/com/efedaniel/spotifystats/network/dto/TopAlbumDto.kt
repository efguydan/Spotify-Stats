package com.efedaniel.spotifystats.network.dto

import com.google.gson.annotations.SerializedName

data class TopAlbumDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: List<ImageDto>,
    @SerializedName("name")
    val name: String,
    @SerializedName("artist")
    val artists: List<String>,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    @SerializedName("uri")
    val uri: String
)