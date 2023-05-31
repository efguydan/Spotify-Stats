package com.efedaniel.spotifystats.domain.mapper

import com.efedaniel.spotifystats.domain.model.TopArtist
import com.efedaniel.spotifystats.network.dto.TopArtistDto
import javax.inject.Inject

class TopArtistMapper @Inject constructor() {

    private fun dtoToDomain(dto: TopArtistDto): TopArtist = TopArtist(
        id = dto.id,
        name = dto.name,
        imageUrl = dto.images.firstOrNull()?.url,
        popularity = dto.popularity,
        genres = dto.genres,
        spotifyUrl = dto.externalUrls.spotify,
        uri = dto.uri,
    )

    fun dtoListToDomainList(dtos: List<TopArtistDto>): List<TopArtist> =
        dtos.map(::dtoToDomain)
}