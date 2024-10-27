package com.efedaniel.spotifystats.ui.scene.auth

import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
import com.efedaniel.spotifystats.ui.proton.theme.ProtonTheme
import com.efedaniel.spotifystats.ui.scene.auth.LoginDestination.MAIN
import com.efedaniel.spotifystats.ui.scene.auth.LoginDestination.SPOTIFY_CONNECT
import com.spotify.sdk.android.auth.AuthorizationClient
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels()
    @Inject lateinit var navigator: LoginNavigator

    private val authResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        val response = AuthorizationClient.getResponse(result.resultCode, result.data)
        viewModel.onConnectSpotifyResponse(response = response)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeFullScreen()
        setContent {
            ProtonTheme {
                LoginScreen(
                    onNewDestination = ::onNewDestination,
                    viewModel = viewModel,
                )
            }
        }
    }

    private fun onNewDestination(destination: LoginDestination) {
        when (destination) {
            SPOTIFY_CONNECT -> navigator.openLoginWithSpotify(activity = this, authResultLauncher = authResultLauncher)
            MAIN -> navigator.navigateToMain(activity = this)
        }
    }

    private fun makeFullScreen() {
        window.run {
            setFlags(FLAG_LAYOUT_NO_LIMITS, FLAG_LAYOUT_NO_LIMITS)
            statusBarColor = Color.TRANSPARENT
        }

        with(WindowCompat.getInsetsController(window, window.decorView)) {
            systemBarsBehavior = BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            hide(WindowInsetsCompat.Type.navigationBars())
        }
    }
}
