package com.efedaniel.spotifystats.network.dto

import com.google.gson.annotations.SerializedName

data class ArtistTopTracksDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("album")
    val album: AlbumDto,
    @SerializedName("artists")
    val artist: ArtistDto,
    @SerializedName("explicit")
    val explicit: Boolean,
    @SerializedName("popularity")
    val popularity: Int,
    @SerializedName("preview_url")
    val previewUrl: String?,
    @SerializedName("uri")
    val uri: String,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    @SerializedName("track_number")
    val trackNumber: Int
)
