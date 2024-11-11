package com.efedaniel.spotifystats.domain.mapper

import com.efedaniel.spotifystats.domain.model.Artist
import com.efedaniel.spotifystats.network.dto.ArtistDto
import javax.inject.Inject

class ArtistMapper @Inject constructor() {

    fun dtoToDomain(dto: ArtistDto): Artist = Artist(
        id = dto.id,
        spotifyUrl = dto.externalUrls.spotify,
        genres = dto.genres,
        imageUrl = dto.images.firstOrNull()?.url,
        name = dto.name,
        popularity = dto.popularity
    )
}

