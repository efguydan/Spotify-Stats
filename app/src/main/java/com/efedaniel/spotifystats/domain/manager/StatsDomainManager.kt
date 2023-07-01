package com.efedaniel.spotifystats.domain.manager

import com.efedaniel.spotifystats.domain.mapper.TopArtistMapper
import com.efedaniel.spotifystats.domain.mapper.TopTrackMapper
import com.efedaniel.spotifystats.domain.model.TimeRange
import com.efedaniel.spotifystats.domain.model.TopArtist
import com.efedaniel.spotifystats.domain.model.TopTrack
import com.efedaniel.spotifystats.network.dto.PaginatedResponse
import com.efedaniel.spotifystats.network.dto.TopArtistDto
import com.efedaniel.spotifystats.network.dto.TopTrackDto
import com.efedaniel.spotifystats.network.service.StatsApi
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class StatsDomainManager @Inject constructor(
    private val statsApi: StatsApi,
    private val topArtistMapper: TopArtistMapper,
    private val topTrackMapper: TopTrackMapper,
) {

    fun getTopArtists(
        timeRange: TimeRange,
        offset: Int,
        limit: Int,
    ): Single<List<TopArtist>> =
        statsApi
            .getTopArtists(
                timeRange = timeRange.key,
                offset = offset,
                limit = limit,
            )
            .map(PaginatedResponse<TopArtistDto>::items)
            .map(topArtistMapper::dtoListToDomainList)

    fun getTopTracks(
        timeRange: TimeRange,
        offset: Int,
        limit: Int,
    ): Single<List<TopTrack>> =
        statsApi
            .getTopTracks(
                timeRange = timeRange.key,
                offset = offset,
                limit = limit,
            )
            .map(PaginatedResponse<TopTrackDto>::items)
            .map(topTrackMapper::dtoListToDomainList)
}