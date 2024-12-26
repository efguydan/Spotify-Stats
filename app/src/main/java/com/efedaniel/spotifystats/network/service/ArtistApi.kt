package com.efedaniel.spotifystats.network.service

import com.efedaniel.spotifystats.network.dto.AlbumDto
import com.efedaniel.spotifystats.network.dto.ArtistDto
import com.efedaniel.spotifystats.network.dto.ArtistTopTracksDto
import com.efedaniel.spotifystats.network.dto.PaginatedResponse
import com.efedaniel.spotifystats.network.dto.TopArtistDto
import com.efedaniel.spotifystats.network.dto.TopTrackDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ArtistApi {

    @GET("artists/{id}")
    fun getArtist(@Path("id") id: String): Single<ArtistDto>

    @GET("artists/{id}/albums")
    fun getArtistAlbum(@Path("id") id: String): Single<PaginatedResponse<AlbumDto>>

    @GET("artists/{id}/top-tracks")
    fun getArtistTopTracks(@Path("id") id: String): Single<ArtistTopTracksDto>

    @GET("artists/{id}/related-artist")
    fun getRelatedArtist(@Path("id") id: String): Single<TopArtistDto>
}