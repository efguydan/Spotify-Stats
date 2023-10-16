package com.efedaniel.spotifystats.network.dto


import com.google.gson.annotations.SerializedName

data class TopTrackDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("album")
    val album: TopAlbumDto,
    @SerializedName("explicit")
    val explicit: Boolean,
    @SerializedName("popularity")
    val popularity: Int,
    @SerializedName("preview_url")
    val previewUrl: String?,
    @SerializedName("uri")
    val uri: String,
)