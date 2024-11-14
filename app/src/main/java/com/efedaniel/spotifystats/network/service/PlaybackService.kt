package com.efedaniel.spotifystats.network.service

import com.efedaniel.spotifystats.network.dto.PlaybackDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface PlaybackService {

    @GET("me/player")
    fun getPlaybackState(): Single<PlaybackDto>
}