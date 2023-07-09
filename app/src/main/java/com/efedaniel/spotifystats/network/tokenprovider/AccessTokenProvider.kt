package com.efedaniel.spotifystats.network.tokenprovider

import com.efedaniel.spotifystats.network.service.AuthApi
import com.efedaniel.spotifystats.persistence.cache.SessionCache
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccessTokenProvider @Inject constructor(
    private val sessionCache: SessionCache,
    private val authApi: AuthApi,
){

    fun refreshToken(): String? {
        val refreshToken = sessionCache.retrieveRefreshToken() ?: return null

        // Fixme: Write this in a safer way
        val tokenResponse = authApi.refreshToken(refreshToken = refreshToken).execute()
        return tokenResponse.body()?.let {
            sessionCache.storeAccessToken(it.accessToken)
            it.accessToken
        }
    }
}