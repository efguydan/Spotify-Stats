package com.efedaniel.spotifystats.network.service

import com.efedaniel.spotifystats.network.dto.AuthenticationResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("/api/token")
    fun authenticate(
        @Field("grant_type") grantType: String,
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String,
    ): Single<AuthenticationResponse>

    @FormUrlEncoded
    @POST("/api/token")
    fun refreshToken(
        @Field("grant_type") grantType: String = "refresh_token",
        @Field("refresh_token") refreshToken: String,
    ): Call<AuthenticationResponse>
}
