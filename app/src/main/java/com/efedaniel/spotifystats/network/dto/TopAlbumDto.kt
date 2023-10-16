package com.efedaniel.spotifystats.network.dto

import com.google.gson.annotations.SerializedName

data class TopAlbumDto(
    @SerializedName("images")
    val images: List<ImageDto>,
    @SerializedName("name")
    val name: String
)