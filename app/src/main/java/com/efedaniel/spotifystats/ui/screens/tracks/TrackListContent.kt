package com.efedaniel.spotifystats.ui.screens.tracks

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.efedaniel.spotifystats.ui.commons.components.TrackCard
import com.efedaniel.spotifystats.ui.commons.getScrim
import com.efedaniel.spotifystats.ui.commons.layouts.DynamicVerticalGrid
import com.efedaniel.spotifystats.ui.theme.SpotifyStatsTheme
import com.efedaniel.spotifystats.utils.StatsTimeFrame
import com.efedaniel.spotifystats.utils.sePaddingModifier
import com.efedaniel.spotifystats.utils.toPx

@Composable
fun TracksListContent(
    modifier: Modifier = Modifier,
    timeFrame: StatsTimeFrame
) {
    ScrollableColumn {
        DynamicVerticalGrid(
            modifier = sePaddingModifier(12.dp).padding(top = 4.dp),
            preferredItemWidth = 120.dp.toPx(ContextAmbient.current).dp
        ) {
            repeat(times = 11) {
                TopTrackCard(modifier = Modifier.padding(all = 4.dp))
            }
        }
    }
}

// TODO Try and fix the way the scrim looks from top to bottom. It is not fading out per se.
@Composable
fun TopTrackCard(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        TrackCard()
        Text(
            text = "1",
            fontSize = 35.sp,
            modifier = Modifier
                .fillMaxWidth()
                .background(brush = getScrim(startY = 100f, endY = 0f))
                .padding(start = 4.dp, top = 4.dp)
        )
    }
}

@Preview
@Composable
fun PreviewTracksListContent() {
    SpotifyStatsTheme { TracksListContent(timeFrame = StatsTimeFrame.WEEKS) }
}
