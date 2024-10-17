package com.efedaniel.spotifystats.ui.scene.routing

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModel
import com.efedaniel.spotifystats.BuildConfig.CLIENT_ID
import com.efedaniel.spotifystats.domain.manager.AuthDomainManager
import com.spotify.sdk.android.auth.AccountsQueryParameters.REDIRECT_URI
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoutingViewModel @Inject constructor(
    private val authDomainManager: AuthDomainManager
) : ViewModel() {

    fun isUserAuthenticated(): Boolean =
        authDomainManager
            .isUserAuthenticated()



    private fun startAuthorizationFlow(contextActivity: Activity, authResultLauncher: ActivityResultLauncher<Intent>) {
        val request = AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI)
                .setScopes(
                    arrayOf(
                        "user-read-private",
                        "playlist-read",
                        "playlist-read-private",

                        )
                )
                .build()

        // Create the login intent
        val authIntent = AuthorizationClient.createLoginActivityIntent(contextActivity, request)

        // Launch the authorization activity using the new ActivityResult API
        authResultLauncher.launch(authIntent)

    }
}