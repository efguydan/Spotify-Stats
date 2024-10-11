package com.efedaniel.spotifystats.ui.scene.routing

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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

           var intent = SpotifyNativeAuthUtil.createAuthActivityIntent(this@RoutingActivity)
            Timber.tag("ROUTINGACTIVITY").d("${intent}")

            println(intent?.data)
            startActivity(intent)
        } else {

        }

    }


}