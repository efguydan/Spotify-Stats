package com.efedaniel.spotifystats.domain.manager

import com.efedaniel.spotifystats.network.service.AuthApi
import com.efedaniel.spotifystats.persistence.cache.SessionCache
import com.efedaniel.spotifystats.utility.constants.Constants
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class AuthDomainManager @Inject constructor(
    private val authApi: AuthApi,
    private val sessionCache: SessionCache,
) {

    fun authenticateUser(code: String): Completable = authApi
        .authenticate(
            grantType = GRANT_TYPE,
            code = code,
            redirectUri = Constants.REDIRECT_URI,
        )
        .doOnSuccess { sessionCache.storeAuthTokens(it.accessToken, it.refreshToken) }
        .ignoreElement()

    fun isUserAuthenticated(): Boolean =
        sessionCache
            .retrieveRefreshToken()
            .isNullOrEmpty()
            .not()

    companion object {
        const val GRANT_TYPE = "authorization_code"
    }
}