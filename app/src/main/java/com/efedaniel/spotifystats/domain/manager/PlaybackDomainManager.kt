package com.efedaniel.spotifystats.domain.manager

import com.efedaniel.spotifystats.domain.mapper.PlaybackMapper
import com.efedaniel.spotifystats.domain.model.Playback
import com.efedaniel.spotifystats.network.service.PlaybackService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class PlaybackDomainManager @Inject constructor(
    private val playbackService: PlaybackService,
    private val mapper: PlaybackMapper,
) {

    fun getPlaybackState(): Single<Playback> =
        playbackService
            .getPlaybackState()
            .map(mapper::dtoToDomain)
}
