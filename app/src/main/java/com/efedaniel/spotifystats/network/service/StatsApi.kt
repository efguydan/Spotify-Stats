package com.efedaniel.spotifystats.network.service

import com.efedaniel.spotifystats.network.dto.PaginatedResponse
import com.efedaniel.spotifystats.network.dto.TopArtistDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface StatsApi {

    @GET("me/top/artists")
    fun getTopArtists(
        @Query("time_range") timeRange: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Single<PaginatedResponse<TopArtistDto>>
}
