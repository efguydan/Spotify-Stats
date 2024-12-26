package com.efedaniel.spotifystats.domain.model

import com.efedaniel.spotifystats.network.dto.AlbumDto
import com.efedaniel.spotifystats.network.dto.ArtistDto
import com.efedaniel.spotifystats.network.dto.ImageDto

data class ArtistTrack(
    val id: String,
    val name: String,
    val imageUrl: String?,
    val isExplicit: Boolean?,
    val popularity: Int,
    val previewUrl: String?,
    val uri: String?,
    val images: List<ImageDto>?,
    /*val artists: List<ArtistDto>?,*/
    val album: AlbumDto
)
