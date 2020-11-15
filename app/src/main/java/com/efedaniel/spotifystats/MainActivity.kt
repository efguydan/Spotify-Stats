package com.efedaniel.spotifystats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.efedaniel.spotifystats.ui.SpotifyStatsTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpotifyStatsTheme {
                MainActivityContent()
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    PreviewMainActivityContent()
}
