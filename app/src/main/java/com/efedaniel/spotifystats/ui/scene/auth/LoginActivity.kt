package com.efedaniel.spotifystats.ui.scene.auth

import android.content.Intent
import android.graphics.Color
import android.net.Uri
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
import com.efedaniel.spotifystats.BuildConfig.CLIENT_ID
import com.efedaniel.spotifystats.ui.proton.theme.ProtonTheme
import com.efedaniel.spotifystats.ui.scene.auth.LoginDestination.MAIN
import com.efedaniel.spotifystats.ui.scene.auth.LoginDestination.SPOTIFY_CONNECT
import com.spotify.sdk.android.auth.AccountsQueryParameters.REDIRECT_URI
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()
    @Inject lateinit var navigator: LoginNavigator

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
            //SPOTIFY_CONNECT -> navigator.openLoginWithSpotify(context = this)
            SPOTIFY_CONNECT -> navigator.openLoginWithSpotify(activity = this, authResultLauncher = authResultLauncher)
            MAIN -> navigator.navigateToMain(activity = this)
        }
    }

    /*override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        viewModel.onConnectSpotifyResult(
            code = intent.data?.getQueryParameter("code"),
            error = intent.data?.getQueryParameter("error"),
        )
    }*/

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


    // ActivityResultLauncher to handle the authorization result
    private val authResultLauncher = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            val intent = result.data
            if (intent != null) {

                CoroutineScope(coroutineContext).launch {

                    val response = AuthorizationClient.getResponse(result.resultCode, intent)

                    Timber.tag("ROUTINGACTIVITY").d("${response.accessToken}")
                    Timber.tag("ROUTINGACTIVITY").d("${response.type}")

                    when (response.type) {
                        AuthorizationResponse.Type.TOKEN -> {
                            viewModel.onConnectSpotifyResultt(
                                accessToken = response.accessToken,
                                error = response.error
                            )
                        }
                        AuthorizationResponse.Type.ERROR -> {
                            viewModel.onConnectSpotifyResultt(
                                accessToken = response.accessToken,
                                error = response.error
                            )
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    //This method is needed to handle to the fallback to customtab if
    // Spotify is not installed on the user's phone
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        //Extract the uri from the intent's data
        val uri = intent.data


        if (uri != null) {
            // Extract the authorization response from the URI
            val response = AuthorizationResponse.fromUri(uri)
            Timber.tag("ROUTINGACTIVITY").d("${response.accessToken}")
            Timber.tag("ROUTINGACTIVITY").d("${response.error}")

            when (response.type) {
                AuthorizationResponse.Type.TOKEN -> {
                    viewModel.onConnectSpotifyResultt(
                        accessToken = response.accessToken,
                        error = response.error
                    )
                }
                AuthorizationResponse.Type.ERROR -> {
                    viewModel.onConnectSpotifyResultt(
                        accessToken = response.accessToken,
                        error = response.error
                    )
                }
                else -> {}
            }
        }
    }

    //This method is for custom tab authorization flow, it is not in use because of the above method
    //which is preferred to this
    fun BrowserAuthFlow() {

        val request =
            AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN,
                Uri.parse("mystats://authenticate").toString())
                .setScopes(
                    arrayOf(
                        "user-read-private",
                        "playlist-read",
                        "playlist-read-private",
                        "streaming"
                    )
                )
                .build()

        // Open the login in the browser
        AuthorizationClient.openLoginInBrowser(this, request)
    }

}
