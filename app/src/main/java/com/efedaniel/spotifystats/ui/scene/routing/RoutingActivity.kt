package com.efedaniel.spotifystats.ui.scene.routing

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.efedaniel.spotifystats.BuildConfig.CLIENT_ID
import com.spotify.sdk.android.auth.AccountsQueryParameters.REDIRECT_URI
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import com.spotify.sdk.android.auth.LoginActivity
import com.spotify.sdk.android.auth.app.SpotifyNativeAuthUtil
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class RoutingActivity : ComponentActivity() {

    private val viewModel: RoutingViewModel by viewModels()
    @Inject lateinit var navigator: RoutingNavigator
    val context: Context = this@RoutingActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }
       // navigateToNextScreen()
        navigateToNextScreenSDK()
    }



    private fun navigateToNextScreen() {
        if (viewModel.isUserAuthenticated()) {
            navigator.navigateToMain(this)
        } else {
            navigator.navigateToLogin(this)
        }

    }

    private fun navigateToNextScreenSDK(){

        if (SpotifyNativeAuthUtil.isSpotifyInstalled(context)) {
            Timber.tag("ROUTINGACTIVITY").d("${SpotifyNativeAuthUtil.isSpotifyInstalled(context)}")

           //var intent = SpotifyNativeAuthUtil.createAuthActivityIntent(this@RoutingActivity)
           // Timber.tag("ROUTINGACTIVITY").d("${intent}")

            val REQUEST_CODE = 1337

            val request = AuthorizationRequest.Builder(
                CLIENT_ID,
                AuthorizationResponse.Type.TOKEN,
                REDIRECT_URI
            )
                .setScopes(
                    arrayOf<String>(
                        "user-read-private",
                        "playlist-read",
                        "playlist-read-private",
                        "streaming"
                    )
                )
                .build()

            AuthorizationClient.openLoginActivity(this, REQUEST_CODE, request)

            val intent = AuthorizationClient.createLoginActivityIntent(this, request)
            startActivity(intent)
            Timber.d(request.toString())
        } else {

        }

    }


}