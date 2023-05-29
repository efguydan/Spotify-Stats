package com.efedaniel.spotifystats.persistence.cache

import com.efedaniel.spotifystats.persistence.prefs.PrefKeys
import com.efedaniel.spotifystats.persistence.prefs.PrefsHelper
import javax.inject.Inject

class SessionCache @Inject constructor(
    private val prefsHelper: PrefsHelper
) {

    fun storeAuthTokens(
        accessToken: String,
        refreshToken: String,
    ) {
        prefsHelper.storeString(PrefKeys.ACCESS_TOKEN, accessToken)
        prefsHelper.storeString(PrefKeys.REFRESH_TOKEN, refreshToken)
    }

    fun retrieveAccessToken(): String? = prefsHelper
        .readString(PrefKeys.ACCESS_TOKEN)

    fun retrieveRefreshToken(): String? = prefsHelper
        .readString(PrefKeys.REFRESH_TOKEN)

    fun clearPrefs() = prefsHelper.clearData()
}
