package com.efedaniel.spotifystats.network.service

import com.efedaniel.spotifystats.network.dto.UserResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface UserApi {

    @GET("me")
    fun getCurrentUser(): Single<UserResponse>
}
