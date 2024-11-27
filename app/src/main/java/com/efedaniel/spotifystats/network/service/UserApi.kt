package com.efedaniel.spotifystats.network.service

import com.efedaniel.spotifystats.network.dto.AlbumDto
import com.efedaniel.spotifystats.network.dto.ArtistDto
import com.efedaniel.spotifystats.network.dto.PaginatedResponse
import com.efedaniel.spotifystats.network.dto.TopAlbumDto
import com.efedaniel.spotifystats.network.dto.TopArtistDto
import com.efedaniel.spotifystats.network.dto.TopTrackDto
import com.efedaniel.spotifystats.network.dto.UserResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("me")
    fun getCurrentUser(): Single<UserResponse>

    
/*
    @GET("artists/{id}")
    fun getArtist(@Path("id") id: String): Single<ArtistDto>

    @GET("artists/{id}/albums")
    fun getArtistAlbum(@Path("id") id: String): Single<PaginatedResponse<AlbumDto>>

    @GET("artist/{id}/tracks")
    fun getArtistTracks(@Path("id") id: String): Single<PaginatedResponse<TopTrackDto>>

    @GET("artist/{id}/related-artist")
    fun getRelatedArtist(@Path("id") id: String): Single<PaginatedResponse<TopArtistDto>>*/

}
