package com.efedaniel.spotifystats.ui.screens.activity

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.efedaniel.spotifystats.R
import com.efedaniel.spotifystats.extensions.sePaddingModifier
import com.efedaniel.spotifystats.extensions.toPx
import com.efedaniel.spotifystats.ui.commons.components.NetworkImage
import com.efedaniel.spotifystats.ui.commons.components.SpotifyStatsAppBar
import com.efedaniel.spotifystats.ui.commons.components.TrackCard
import com.efedaniel.spotifystats.ui.commons.getScrim
import com.efedaniel.spotifystats.ui.commons.layouts.DynamicVerticalGrid
import com.efedaniel.spotifystats.ui.theme.SpotifyStatsTheme
import com.efedaniel.spotifystats.utils.Constants

@Composable
fun ActivityScreen() {
    SpotifyStatsTheme {
        Scaffold(
            topBar = { SpotifyStatsAppBar(title = stringResource(R.string.activity)) }
        ) {
            ScrollableColumn(
                contentPadding = it
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Currently Playing", style = MaterialTheme.typography.h6, modifier = sePaddingModifier())
                Spacer(modifier = Modifier.height(8.dp))
                CurrentlyPlayingTrackCard(modifier = sePaddingModifier())
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Recently Played", style = MaterialTheme.typography.h6, modifier = sePaddingModifier())

                DynamicVerticalGrid(
                    modifier = sePaddingModifier(12.dp).padding(top = 4.dp),
                    preferredItemWidth = 120.dp.toPx(ContextAmbient.current).dp
                ) {
                    repeat(times = 11) {
                        RecentlyPlayedTrackCard(modifier = Modifier.padding(all = 4.dp))
                    }
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
            NetworkImage(url = Constants.Misc.WEEKND_IMAGE, modifier = Modifier.padding(8.dp).size(100.dp))
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
fun RecentlyPlayedTrackCard(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        TrackCard()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(brush = getScrim(startY = 0f, endY = 40f))
                .padding(top = 4.dp, start = 4.dp, bottom = 8.dp)
        ) {
            Icon(Icons.Filled.History, modifier = Modifier.size(12.dp).align(Alignment.CenterVertically))
            Spacer(modifier = Modifier.width(2.dp))
            Text(text = "Just Now", fontSize = 12.sp)
        }
    }
}

@Preview
@Composable
fun PreviewActivityScreen() {
    ActivityScreen()
}
