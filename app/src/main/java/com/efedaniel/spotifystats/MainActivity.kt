package com.efedaniel.spotifystats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.AmbientContentColor
import androidx.compose.foundation.Text
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
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

@Composable
fun MainActivityContent() {
    SpotifyStatsTheme {
        Scaffold(
            bottomBar = {
                BottomNavigation {
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Home, tint = Color.White) },
                        selected = true,
                        onClick = { /* Will handle later*/ },
                        label = { Text("Tracks") }
                    )
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Home, tint = Color.White) },
                        selected = true,
                        onClick = { /* Will handle later*/ },
                        label = { Text("Artists") }
                    )
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Home, tint = Color.White) },
                        selected = true,
                        onClick = { /* Will handle later*/ },
                        label = { Text("Recents") }
                    )
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Home, tint = Color.White) },
                        selected = true,
                        onClick = { /* Will handle later*/ },
                        label = { Text("Insights") }
                    )
                }
            }
        ) {

        }
    }
}

@Preview
@Composable
fun PreviewMainActivityContent() {
    MainActivityContent()
}
