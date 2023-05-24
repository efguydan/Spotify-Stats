package com.efedaniel.spotifystats.network.dto


import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("url")
    val url: String,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("width")
    val width: Int?
)