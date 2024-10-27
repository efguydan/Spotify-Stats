package com.efedaniel.spotifystats.ui.scene.auth

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.efedaniel.spotifystats.BuildConfig.CLIENT_ID
import com.efedaniel.spotifystats.ui.scene.main.MainActivity
import com.efedaniel.spotifystats.utility.constants.Constants.REDIRECT_URI
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import javax.inject.Inject


class LoginNavigator @Inject constructor() {

    fun navigateToMain(activity: Activity) {
        activity.run {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun openLoginWithSpotify(activity: Activity, authResultLauncher: ActivityResultLauncher<Intent>) {
        val request = AuthorizationRequest
            .Builder(
                CLIENT_ID,
                AuthorizationResponse.Type.TOKEN,
                REDIRECT_URI,
            )
            .setScopes(
                arrayOf(
                    "user-read-private",
                    "playlist-read",
                    "user-top-read",
                    "user-read-recently-played"
                )
            )
            .build()

        val authIntent = AuthorizationClient.createLoginActivityIntent(activity, request)
        authResultLauncher.launch(authIntent)
    }
}