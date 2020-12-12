package com.efedaniel.spotifystats.data.apis

import retrofit2.http.POST

interface AuthService {

    @POST("/api/token")
    suspend fun getAccessToken()

}