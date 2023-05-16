package com.efedaniel.spotifystats.ui.scene.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.efedaniel.spotifystats.ui.proton.theme.SpotifyStatsTheme

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpotifyStatsTheme {
                LoginScreen()
            }
        }
    }
}
