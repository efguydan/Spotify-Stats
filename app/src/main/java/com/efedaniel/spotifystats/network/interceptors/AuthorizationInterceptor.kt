package com.efedaniel.spotifystats.network.interceptors

import android.util.Base64
import com.efedaniel.spotifystats.BuildConfig
import com.efedaniel.spotifystats.persistence.cache.SessionCache
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthorizationInterceptor @Inject constructor(
    private val sessionCache: SessionCache,
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()

        val authTokenValue = if (original.url.toString().contains("api/token")) {
            val client = "${BuildConfig.CLIENT_ID}:${BuildConfig.CLIENT_SECRET}"
            val token = Base64.encodeToString(client.toByteArray(), Base64.NO_WRAP)
            "Basic $token"
        } else {
            val token = sessionCache.retrieveAccessToken()
            "Bearer $token"
        }

        val updated = builder
            .addHeader(ACCEPT_HEADER_KEY, ACCEPT_HEADER_VALUE)
            .addHeader(AUTH_HEADER_KEY, authTokenValue)
            .build()

        return chain.proceed(updated)
    }

    private companion object {
        const val AUTH_HEADER_KEY = "Authorization"
        const val ACCEPT_HEADER_KEY = "Accept"
        const val ACCEPT_HEADER_VALUE = "application/json"
    }
}