package com.efedaniel.spotifystats.network.dto

import com.google.gson.annotations.SerializedName

data class AlbumDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: List<ImageDto>,
    @SerializedName("name")
    val name: String,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("release_date_precision")
    val releaseDateProduction : String,
    @SerializedName("type")
    val type: String,
    @SerializedName("uri")
    val uri: String,
    @SerializedName("artist")
    val artists: List<String>,
    @SerializedName("total_tracks")
    val totalTracks: Int,
)
