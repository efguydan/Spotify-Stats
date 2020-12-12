package com.efedaniel.spotifystats.ui.commons.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.efedaniel.spotifystats.ui.commons.getScrim
import com.efedaniel.spotifystats.ui.theme.SpotifyStatsTheme
import com.efedaniel.spotifystats.utils.Constants

@Composable
fun TrackCard(modifier: Modifier = Modifier) {
    Card(modifier = modifier.height(140.dp)) {
        Box {
            NetworkImage(url = Constants.Misc.WEEKND_IMAGE)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(brush = getScrim(startY = 100f, endY = 0f))
            ) {
                Text(
                    text = "The Weeknd",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(top = 16.dp, start = 4.dp)
                )
                Text(
                    text = "Blinding Lights",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(bottom = 4.dp, start = 4.dp),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewTrackCard() {
    SpotifyStatsTheme { TrackCard() }
}
