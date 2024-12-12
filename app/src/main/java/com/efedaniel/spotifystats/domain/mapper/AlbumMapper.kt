package com.efedaniel.spotifystats.domain.mapper

import com.efedaniel.spotifystats.domain.model.Album
import com.efedaniel.spotifystats.domain.model.TopTrack
import com.efedaniel.spotifystats.network.dto.AlbumDto
import com.efedaniel.spotifystats.network.dto.TopAlbumDto
import com.efedaniel.spotifystats.network.dto.TopTrackDto
import timber.log.Timber
import javax.inject.Inject

class AlbumMapper @Inject constructor(){
    fun dtoToDomain(
        dto: AlbumDto,
        position: Int?
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
    ) = dtos.mapIndexed { index, albumDto ->
        Timber.e(albumDto.name)
        dtoToDomain(dto = albumDto, position = index + 1)
    }
}