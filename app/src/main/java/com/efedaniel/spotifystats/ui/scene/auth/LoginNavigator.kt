package com.efedaniel.spotifystats.ui.scene.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.browser.customtabs.CustomTabsIntent
import com.efedaniel.spotifystats.BuildConfig
import com.efedaniel.spotifystats.BuildConfig.CLIENT_ID
import com.efedaniel.spotifystats.ui.scene.main.MainActivity
import com.efedaniel.spotifystats.utility.constants.Constants.REDIRECT_URI
import com.efedaniel.spotifystats.utility.constants.Constants.SPOTIFY_AUTH_BASE_URL
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class LoginNavigator @Inject constructor() {

    fun openLoginWithSpotify(
        context: Context,
    ) {
        val uri = Uri
            .parse(SPOTIFY_AUTH_BASE_URL)
            .buildUpon()
            .appendPath(AUTHORIZE_PATH)
            .appendQueryParameter(SCOPE_KEY, scopes)
            .appendQueryParameter(RESPONSE_TYPE_KEY, RESPONSE_TYPE)
            .appendQueryParameter(CLIENT_ID_KEY, BuildConfig.CLIENT_ID)
            .appendQueryParameter(REDIRECT_URI_KEY, REDIRECT_URI)
            .build()

        CustomTabsIntent
            .Builder()
            .build()
            .launchUrl(context, uri)
    }

    fun navigateToMain(activity: Activity) {
        activity.run {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private companion object {
        val scopes = listOf(
            "user-read-private",
            "user-top-read",
            "user-read-recently-played"
        ).joinToString(separator = " ")

        const val AUTHORIZE_PATH = "authorize"
        const val SCOPE_KEY = "scope"
        const val CLIENT_ID_KEY = "client_id"
        const val RESPONSE_TYPE_KEY = "response_type"
        const val REDIRECT_URI_KEY = "redirect_uri"

        const val RESPONSE_TYPE = "code"
    }

    fun openLoginWithSpotify(activity: Activity, authResultLauncher: ActivityResultLauncher<Intent>) {
        val request =
            AuthorizationRequest.Builder(
                CLIENT_ID, AuthorizationResponse.Type.TOKEN,
                Uri.parse("mystats://authenticate").toString())
                .setScopes(
                    arrayOf(
                        "user-read-private",
                        "playlist-read",
                        "playlist-read-private",
                    )
                )
                .build()

        // Create the login intent
        val authIntent = AuthorizationClient.createLoginActivityIntent(activity, request)

        // Launch the authorization activity using the new ActivityResult API
        authResultLauncher.launch(authIntent)
    }
}