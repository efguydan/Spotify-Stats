package com.efedaniel.spotifystats.ui.commons.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
fun ArtistCard(
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.height(140.dp)) {
        Box {
            NetworkImage(url = Constants.Misc.siaImage)
            Text(
                text = "Sia",
                style = MaterialTheme.typography.h6,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = getScrim(startY = 100f, endY = 0f))
                    .padding(bottom = 4.dp, start = 4.dp, top = 16.dp)
                    .align(Alignment.BottomStart)
            )
        }
    }
}

@Preview
@Composable
fun PreviewArtistCard() {
    SpotifyStatsTheme { ArtistCard() }
}
