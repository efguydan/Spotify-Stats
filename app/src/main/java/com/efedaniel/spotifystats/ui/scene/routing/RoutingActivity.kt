package com.efedaniel.spotifystats.ui.scene.routing


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.efedaniel.spotifystats.BuildConfig.CLIENT_ID
import com.spotify.sdk.android.auth.AccountsQueryParameters.REDIRECT_URI
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
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
       // navigateToNextScreenSDK()
        startAuthorizationFlow()
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

           var intentt = SpotifyNativeAuthUtil.createAuthActivityIntent(this@RoutingActivity)
            Timber.tag("ROUTINGACTIVITY").d("${intentt}")

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


    private fun startAuthorizationFlow() {
        val request =
            AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI)
                .setScopes(
                    arrayOf(
                       // "user-read-private",
                        "playlist-read",
                      //  "playlist-read-private",

                    )
                )
                .build()

        // Create the login intent
        val authIntent = AuthorizationClient.createLoginActivityIntent(this, request)

        // Launch the authorization activity using the new ActivityResult API
        authResultLauncher.launch(authIntent)
    }


    // ActivityResultLauncher to handle the authorization result
    private val authResultLauncher = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            val intent = result.data
            if (intent != null) {
                // Handle the authorization response
                val response = AuthorizationClient.getResponse(result.resultCode, intent)
                Timber.tag("ROUTINGACTIVITY").d("${response}")
                Timber.tag("ROUTINGACTIVITY").d("${response.accessToken}")
                Timber.tag("ROUTINGACTIVITY").d("${response.type}")
                Timber.tag("ROUTINGACTIVITY").d("${response.expiresIn}")

                when (response.type) {


                    AuthorizationResponse.Type.TOKEN -> {
                        Timber.tag("ROUTINGACTIVITY").d("${response.accessToken}")
                        Timber.tag("ROUTINGACTIVITY").d("${response.type}")
                        Timber.tag("ROUTINGACTIVITY").d("${response.expiresIn}")
                    }                            // Extract the access token
                       /* var token
                        : String
                        ?
                        = response.accessToken*/
                    AuthorizationResponse.Type.ERROR -> {
                        Timber.tag("ROUTINGACTIVITY").d("${response.accessToken}")
                        Timber.tag("ROUTINGACTIVITY").d("${response.type}")
                        Timber.tag("ROUTINGACTIVITY").d("${response.expiresIn}")
                    }
                    else -> {}
                }
            }
        }
    }

}