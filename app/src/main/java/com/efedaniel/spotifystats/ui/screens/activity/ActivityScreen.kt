package com.efedaniel.spotifystats.ui.screens.activity

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.efedaniel.spotifystats.R
import com.efedaniel.spotifystats.ui.commons.DynamicVerticalGrid
import com.efedaniel.spotifystats.ui.commons.SpotifyStatsAppBar
import com.efedaniel.spotifystats.ui.theme.SpotifyStatsTheme
import com.efedaniel.spotifystats.utils.Constants
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun ActivityScreen() {
    SpotifyStatsTheme {
        Scaffold(
            topBar = { SpotifyStatsAppBar(title = stringResource(R.string.activity)) }
        ) {
            ScrollableColumn(
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(text = "Currently Playing", style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.height(8.dp))
                CurrentlyPlayingTrackCard()
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Recently Played", style = MaterialTheme.typography.h6)

                DynamicVerticalGrid(
                    preferredItemWidth = 120.dp,
                    preferredColumns = null
                ) {
                    TrackCard(modifier = Modifier.padding(top = 8.dp))
                    TrackCard(modifier = Modifier.padding(top = 8.dp))
                    TrackCard(modifier = Modifier.padding(top = 8.dp))
                    TrackCard(modifier = Modifier.padding(top = 8.dp))
                    TrackCard(modifier = Modifier.padding(top = 8.dp))
                    TrackCard(modifier = Modifier.padding(top = 8.dp))
                    TrackCard(modifier = Modifier.padding(top = 8.dp))
                    TrackCard(modifier = Modifier.padding(top = 8.dp))
                    TrackCard(modifier = Modifier.padding(top = 8.dp))
                    TrackCard(modifier = Modifier.padding(top = 8.dp))
                }
            }
        }
    }
}

@Composable
fun CurrentlyPlayingTrackCard(modifier: Modifier = Modifier) {
    Card(
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(Modifier.fillMaxWidth()) {
            TrackImage(modifier = Modifier.padding(8.dp))
            Column(
                modifier = Modifier.fillMaxHeight().weight(1f),
            ) {
                Text(
                    text = "Blinding Lights",
                    modifier = Modifier.padding(top = 6.dp),
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "The Weeknd",
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = "After Hours",
                    modifier = Modifier.padding(bottom = 6.dp),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
fun TrackImage(modifier: Modifier = Modifier) {
    CoilImage(
        data = Constants.Misc.randomImage,
        contentScale = ContentScale.Crop,
        fadeIn = true,
        modifier = modifier.size(100.dp).clip(MaterialTheme.shapes.medium),
    )
}

// TODO Move these to appropriate files later

@Composable
fun TrackCard(modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Box {
            CoilImage(
                data = Constants.Misc.randomImage,
                contentScale = ContentScale.Crop,
                fadeIn = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
        }
    }
}

// Previews

@Preview
@Composable
fun PreviewActivityScreen() {
    ActivityScreen()
}

@Preview
@Composable
fun PreviewCurrentlyPlayingTrackCard() {
    SpotifyStatsTheme { CurrentlyPlayingTrackCard() }
}

@Preview
@Composable
fun PreviewTrackCard() {
    SpotifyStatsTheme { TrackCard() }
}
