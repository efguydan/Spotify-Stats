package com.efedaniel.spotifystats.network.interceptors

import com.efedaniel.spotifystats.network.tokenprovider.AccessTokenProvider
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val tokenProvider: AccessTokenProvider,
): Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        synchronized(this) {
            val newToken = tokenProvider.refreshToken() ?: return null

            return response
                .request
                .newBuilder()
                .removeHeader(AUTH_HEADER)
                .addHeader(AUTH_HEADER, "Bearer $newToken")
                .build()
        }
    }

    private companion object {
        const val AUTH_HEADER = "Authorization"
    }
}