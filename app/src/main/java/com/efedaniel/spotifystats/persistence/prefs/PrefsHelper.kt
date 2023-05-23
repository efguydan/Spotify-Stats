package com.efedaniel.spotifystats.persistence.prefs

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class PrefsHelper @Inject constructor(
    private val prefs: SharedPreferences
) {

    fun storeString(
        key: String,
        value: String
    ) {
        prefs.edit {
            putString(key, value)
        }
    }

    fun readString(
        key: String,
        defaultValue: String? = null
    ): String? = prefs.getString(key, defaultValue)
}