package com.efedaniel.spotifystats.domain.mapper

import com.efedaniel.spotifystats.domain.model.ArtistTrack
import com.efedaniel.spotifystats.network.dto.ArtistTrackDto
import javax.inject.Inject

class ArtistTrackMapper @Inject constructor() {

    private fun dtoToDomain(
        dto: ArtistTrackDto,
    ) = ArtistTrack(
        id = dto.id,
        name = dto.name,
        imageUrl = dto.images?.firstOrNull()?.url,
        popularity = dto.popularity,
        isExplicit = dto.explicit,
        previewUrl = dto.previewUrl,
        uri = dto.uri,
        images = dto.images,
        album = dto.album,
    )

    fun dtoListToDomainList(
        dtos: List<ArtistTrackDto>
    ) = dtos.map { artistTrackDto ->
        dtoToDomain(dto = artistTrackDto)
    }
}