package com.efedaniel.spotifystats.domain.mapper

import com.efedaniel.spotifystats.domain.model.Album
import com.efedaniel.spotifystats.domain.model.ArtistTrack
import com.efedaniel.spotifystats.network.dto.AlbumDto
import com.efedaniel.spotifystats.network.dto.ArtistTopTracksDto
import com.efedaniel.spotifystats.network.dto.ArtistTrackDto
import timber.log.Timber
import javax.inject.Inject

class ArtistTrackMapper @Inject constructor() {

            private fun dtoToDomain(
                dto: ArtistTrackDto,
                position:Int
            ) :
                ArtistTrack = ArtistTrack(
                    id = dto.id,
                    name = dto.name,
             //     imageUrl = dto.images.firstOrNull()?.url,
                    popularity = dto.popularity,
                    isExplicit = dto.explicit,
                    previewUrl = dto.previewUrl,
                    uri = dto.uri,
                    images = dto.images,
                    album = dto.album
                )

            fun dtoListToDomainList(
                dtos: List<ArtistTrackDto>
            ) =
                dtos.mapIndexed { index, artistTrackDto ->
                    dtoToDomain(dto = artistTrackDto, position = index + 1)
            }
}