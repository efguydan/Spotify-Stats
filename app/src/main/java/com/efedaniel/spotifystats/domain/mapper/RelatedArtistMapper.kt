package com.efedaniel.spotifystats.domain.mapper

import com.efedaniel.spotifystats.domain.model.RelatedArtist
import com.efedaniel.spotifystats.network.dto.RelatedArtistDto
import javax.inject.Inject

class RelatedArtistMapper @Inject constructor() {
    private fun dtoToDomain(
        dto: RelatedArtistDto,
        position:Int
    ) {
        val relatedArtist = RelatedArtist(
            id = dto.id,
            name = dto.name,
            imageUrl = dto.images.firstOrNull()?.url,
            popularity = dto.popularity,
            followersDto = dto.followers,
            genres = dto.genres
        )
    }

    fun dtoListToDomainList(
        dtos: List<RelatedArtistDto>
    ) {
        dtos.mapIndexed { index, relatedArtistDto ->
            dtoToDomain(dto = relatedArtistDto, position = index + 1)
        }
    }
}