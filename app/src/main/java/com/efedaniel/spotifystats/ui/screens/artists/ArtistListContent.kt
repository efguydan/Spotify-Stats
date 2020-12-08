package com.efedaniel.spotifystats.ui.screens.artists

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.efedaniel.spotifystats.ui.commons.components.ArtistCard
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
                ArtistCard(modifier = Modifier.padding(all = 4.dp))
            }
        }
    }
}

@Preview
@Composable
fun PreviewArtistListContent() {
    SpotifyStatsTheme { ArtistListContent(timeFrame = StatsTimeFrame.MONTHS) }
}
