package com.efedaniel.spotifystats.domain.manager

import com.efedaniel.spotifystats.network.dto.AuthenticationResponse
import com.efedaniel.spotifystats.network.service.AuthApi
import com.efedaniel.spotifystats.utility.constants.Constants
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AuthDomainManager @Inject constructor(
    private val authApi: AuthApi,
) {

    fun authenticateUser(code: String): Single<AuthenticationResponse> = authApi
        .authenticate(
            grantType = GRANT_TYPE,
            code = code,
            redirectUri = Constants.REDIRECT_URI,
        )

    companion object {
        const val GRANT_TYPE = "authorization_code"
    }
}