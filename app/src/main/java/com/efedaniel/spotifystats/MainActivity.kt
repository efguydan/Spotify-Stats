package com.efedaniel.spotifystats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.efedaniel.spotifystats.ui.proton.theme.SpotifyStatsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpotifyStatsTheme {
                Text(text = "Hi")
            }
        }
    }
}