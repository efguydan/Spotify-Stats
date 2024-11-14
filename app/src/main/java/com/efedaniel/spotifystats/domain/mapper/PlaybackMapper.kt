package com.efedaniel.spotifystats.domain.mapper

import com.efedaniel.spotifystats.domain.model.Playback
import com.efedaniel.spotifystats.network.dto.PlaybackDto
import javax.inject.Inject

class PlaybackMapper @Inject constructor() {

    fun dtoToDomain(
        dto: PlaybackDto,
    ): Playback = Playback(
        isPlaying = dto.isPlaying,
    )
}