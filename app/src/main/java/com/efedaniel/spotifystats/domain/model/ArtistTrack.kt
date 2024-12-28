package com.efedaniel.spotifystats.domain.model

import androidx.compose.runtime.Immutable
import com.efedaniel.spotifystats.network.dto.AlbumDto
import com.efedaniel.spotifystats.network.dto.ImageDto

@Immutable
data class ArtistTrack(
    val id: String,
    val name: String,
    val imageUrl: String?,
    val isExplicit: Boolean?,
    val popularity: Int?,
    val previewUrl: String?,
    val uri: String?,
    val images: List<ImageDto>?,
    val album: AlbumDto?
)
