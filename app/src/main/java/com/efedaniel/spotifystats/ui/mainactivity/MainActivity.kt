package com.efedaniel.spotifystats.ui.mainactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.efedaniel.spotifystats.ui.theme.SpotifyStatsTheme

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
