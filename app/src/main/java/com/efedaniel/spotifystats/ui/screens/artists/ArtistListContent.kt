package com.efedaniel.spotifystats.ui.screens.artists

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.efedaniel.spotifystats.ui.commons.components.ArtistCard
import com.efedaniel.spotifystats.ui.commons.getScrim
import com.efedaniel.spotifystats.ui.commons.layouts.DynamicVerticalGrid
import com.efedaniel.spotifystats.ui.theme.SpotifyStatsTheme
import com.efedaniel.spotifystats.utils.StatsTimeFrame
import com.efedaniel.spotifystats.utils.sePaddingModifier
import com.efedaniel.spotifystats.utils.toPx

@Composable
fun ArtistListContent(
    modifier: Modifier = Modifier,
    timeFrame: StatsTimeFrame
) {
    ScrollableColumn {
        DynamicVerticalGrid(
            modifier = sePaddingModifier(12.dp).padding(top = 4.dp),
            preferredItemWidth = 120.dp.toPx(ContextAmbient.current).dp
        ) {
            repeat(times = 11) {
                TopArtistCard(modifier = Modifier.padding(all = 4.dp))
            }
        }
    }
}

// TODO Try and fix the way the scrim looks from top to bottom. It is not fading out per se.
// TODO Also Consider using a radial scrim

@Composable
fun TopArtistCard(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        ArtistCard()
        Text(
            text = "1",
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .fillMaxWidth()
                .background(brush = getScrim())
                .padding(start = 8.dp, top = 4.dp)
        )
    }
}

@Preview
@Composable
fun PreviewArtistListContent() {
    SpotifyStatsTheme { ArtistListContent(timeFrame = StatsTimeFrame.MONTHS) }
}

@Preview
@Composable
fun PreviewTopArtistCard() {
    SpotifyStatsTheme { TopArtistCard() }
}
