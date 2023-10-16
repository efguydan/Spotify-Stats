package com.efedaniel.spotifystats.domain.mapper

import com.efedaniel.spotifystats.domain.model.TopTrack
import com.efedaniel.spotifystats.network.dto.TopTrackDto
import timber.log.Timber
import javax.inject.Inject

class TopTrackMapper @Inject constructor() {

    private fun dtoToDomain(
        dto: TopTrackDto,
        position: Int
    ): TopTrack = TopTrack(
        id = dto.id,
        position = position,
        name = dto.name,
        imageUrl = dto.album.images.firstOrNull()?.url,
        isExplicit = dto.explicit,
        popularity = dto.popularity,
        previewUrl = dto.previewUrl,
        uri = dto.uri,
    )

    fun dtoListToDomainList(
        dtos: List<TopTrackDto>
    ) = dtos.mapIndexed { index, trackDto ->
        Timber.e(trackDto.previewUrl)
        dtoToDomain(trackDto, index + 1)
    }
}