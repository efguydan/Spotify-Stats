package com.efedaniel.spotifystats.domain.model

import com.efedaniel.spotifystats.network.dto.AlbumDto
import com.efedaniel.spotifystats.network.dto.ArtistDto
import com.efedaniel.spotifystats.network.dto.ImageDto

data class ArtistTrack(
    val id: String = "",
    val name: String = "",
    val imageUrl: String? = "",
    val isExplicit: Boolean? = null,
    val popularity: Int? = 0,
    val previewUrl: String? ="",
    val uri: String? = "",
    val images: List<ImageDto>? = emptyList(),
    val album: AlbumDto? = null
)
