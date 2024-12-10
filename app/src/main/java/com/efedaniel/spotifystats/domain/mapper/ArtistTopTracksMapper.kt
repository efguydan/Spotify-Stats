package com.efedaniel.spotifystats.domain.mapper

import com.efedaniel.spotifystats.domain.model.ArtistTopTracks
import com.efedaniel.spotifystats.network.dto.ArtistTopTracksDto
import javax.inject.Inject

class ArtistTopTracksMapper @Inject constructor() {

    private fun dtoToDomain(
        dto: ArtistTopTracksDto,
        position:Int
    ) {
        val artistTopTracks = ArtistTopTracks(
            id = dto.id,
            name = dto.name,
            imageUrl = dto.images.firstOrNull()?.url,
            popularity = dto.popularity,
            isExplicit = dto.explicit,
            previewUrl = dto.previewUrl,
            uri = dto.uri,
            images = dto.images,
        )
    }

    fun dtoListToDomainList(
        dtos: List<ArtistTopTracksDto>
    ) {
        dtos.mapIndexed { index, artistTopTracksDto ->
            dtoToDomain(dto = artistTopTracksDto, position = index + 1)
        }

    }
}