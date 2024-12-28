package com.efedaniel.spotifystats.domain.mapper

import com.efedaniel.spotifystats.domain.model.Album
import com.efedaniel.spotifystats.network.dto.AlbumDto
import javax.inject.Inject

class AlbumMapper @Inject constructor() {

    fun dtoToDomain(
        dto: AlbumDto,
    ): Album = Album(
        id = dto.id,
        name = dto.name,
        imageUrl = dto.images.firstOrNull()?.url,
        artists = dto.artists,
        spotifyUrl = dto.externalUrls.spotify,
        uri = dto.uri,
    )

    fun dtoListToDomainList(
        dtos: List<AlbumDto>
    ) = dtos.map { albumDto ->
        dtoToDomain(dto = albumDto)
    }
}