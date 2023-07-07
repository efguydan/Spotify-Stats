package com.efedaniel.spotifystats.network.interceptors.tokenprovider

interface AccessTokenProvider {

    fun refreshToken(): String?
}