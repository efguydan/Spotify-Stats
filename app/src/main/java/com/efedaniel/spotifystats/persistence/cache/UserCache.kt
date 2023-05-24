package com.efedaniel.spotifystats.persistence.cache

import com.efedaniel.spotifystats.persistence.entity.UserEntity
import com.efedaniel.spotifystats.persistence.prefs.PrefKeys
import com.efedaniel.spotifystats.persistence.prefs.PrefsHelper
import javax.inject.Inject

class UserCache @Inject constructor(
    private val prefsHelper: PrefsHelper
) {

    fun storeUser(user: UserEntity) {
        prefsHelper.storeObject(PrefKeys.CURRENT_USER, user)
    }

    fun retrieveUser(): UserEntity? =
        prefsHelper.readObject(
            key = PrefKeys.CURRENT_USER,
            type = UserEntity::class.java
        )
}