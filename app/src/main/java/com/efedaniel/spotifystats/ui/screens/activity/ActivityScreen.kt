package com.efedaniel.spotifystats.ui.screens.activity

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.efedaniel.spotifystats.ui.theme.SpotifyStatsTheme
import com.efedaniel.spotifystats.utils.Constants
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun ActivityScreen() {
    SpotifyStatsTheme {
        Row {
            CurrentlyPlayingTrackCard()
        }
    }
}

@Composable
fun CurrentlyPlayingTrackCard(modifier: Modifier = Modifier) {
    Box(modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp).fillMaxWidth()) {
        Card {
            TrackImageCard()
        }
    }
}

@Composable
fun TrackImageCard(modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(80.dp)) {
        CoilImage(
            data = Constants.Misc.randomImage,
            contentScale = ContentScale.Crop,
            fadeIn = true
        )
    }
}
