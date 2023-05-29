package com.efedaniel.spotifystats.persistence.prefs

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import timber.log.Timber
import javax.inject.Inject

class PrefsHelper @Inject constructor(
    private val prefs: SharedPreferences,
    private val gson: Gson,
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

    fun storeObject(
        key: String,
        value: Any
    ) {
        prefs.edit {
            putString(key, gson.toJson(value))
        }
    }

    fun <T> readObject(
        key: String,
        defaultValue: T? = null,
        type: Class<T>,
    ): T? {
        return try {
            gson.fromJson(prefs.getString(key, null), type)
        } catch (exception: NullPointerException) {
            Timber.e(exception)
            defaultValue
        } catch (exception: JsonSyntaxException) {
            Timber.e(exception)
            defaultValue
        }
    }

    fun clearData() = prefs.edit { clear() }
}