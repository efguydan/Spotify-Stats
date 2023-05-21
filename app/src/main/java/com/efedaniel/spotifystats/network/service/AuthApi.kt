package com.efedaniel.spotifystats.network.service

import com.efedaniel.spotifystats.network.dto.AuthenticationResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {

    @POST("/api/token")
    fun authenticate(
        @Query("grant_type") grantType: String,
        @Query("code") code: String,
        @Query("redirect_uri") redirectUri: String,
    ): Single<AuthenticationResponse>
}
