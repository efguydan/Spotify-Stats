package com.efedaniel.spotifystats.network.interceptors

import com.efedaniel.spotifystats.network.service.AuthApi
import com.efedaniel.spotifystats.persistence.cache.SessionCache
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val sessionCache: SessionCache,
    private val authApi: AuthApi,
): Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = sessionCache.retrieveRefreshToken() ?: return null

        val tokenResponse = authApi.refreshToken(refreshToken = refreshToken)
        sessionCache.storeAccessToken(tokenResponse.accessToken)

        return response
            .request
            .newBuilder()
            .removeHeader(AUTH_HEADER)
            .addHeader(AUTH_HEADER, tokenResponse.accessToken)
            .build()
    }

    private companion object {
        const val AUTH_HEADER = "Authorization"
    }
}