package com.efedaniel.spotifystats.network.service

import com.efedaniel.spotifystats.network.dto.ArtistDto
import com.efedaniel.spotifystats.network.dto.UserResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("me")
    fun getCurrentUser(): Single<UserResponse>

    @GET("artists/{id}")
    fun getArtist(@Path("id") id: String): Single<ArtistDto>
}