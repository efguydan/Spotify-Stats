package com.efedaniel.spotifystats.domain.mapper

import com.efedaniel.spotifystats.domain.model.Artist
import com.efedaniel.spotifystats.network.dto.ArtistDto
import javax.inject.Inject

class SeveralArtistsMapper @Inject constructor() {

    fun dtoToDomain(
        dto: ArtistDto,
    ): Artist = Artist(
        id = dto.id,
        name = dto.name,
        imageUrl = dto.images.firstOrNull()?.url,
        spotifyUrl = dto.externalUrls.spotify,
        genres = dto.genres,
        popularity = dto.popularity,
    )

    fun dtoListToDomainList(
        dtos: List<ArtistDto>
    ) = dtos.map { artistDto ->
        dtoToDomain(dto = artistDto)
    }
}