package com.efedaniel.spotifystats.ui.screens.tracks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.ui.tooling.preview.Preview
import com.efedaniel.spotifystats.R
import com.efedaniel.spotifystats.ui.commons.components.SpotifyStatsAppBar
import com.efedaniel.spotifystats.ui.commons.layouts.TabLayout
import com.efedaniel.spotifystats.ui.screens.tracks.TracksTimeFrame.ALL_TIME
import com.efedaniel.spotifystats.ui.screens.tracks.TracksTimeFrame.MONTHS
import com.efedaniel.spotifystats.ui.screens.tracks.TracksTimeFrame.WEEKS
import com.efedaniel.spotifystats.ui.theme.SpotifyStatsTheme

@Composable
fun TracksScreen() {
    SpotifyStatsTheme {
        Scaffold(
            // TODO Fix elevation of this app bar
            topBar = { SpotifyStatsAppBar(title = stringResource(R.string.tracks)) }
        ) {
            val (selectedIndex, updateSelectedIndex) = remember { mutableStateOf(WEEKS.ordinal) }
            Column(modifier = Modifier.fillMaxSize()) {
                TabLayout(
                    selectedIndex = selectedIndex,
                    updateSelectedIndex = updateSelectedIndex,
                    tabsName = remember { TracksTimeFrame.values().map { it.title } }
                )
                TracksPagerContent(selectedIndex, updateSelectedIndex, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun TracksPagerContent(
    selectedIndex: Int,
    updateSelectedIndex: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    // TODO Come later to update selected Index based on left and right scrolls
    when (selectedIndex) {
        WEEKS.ordinal -> TracksListContent(timeFrame = WEEKS, modifier = modifier)
        MONTHS.ordinal -> TracksListContent(timeFrame = MONTHS, modifier = modifier)
        ALL_TIME.ordinal -> TracksListContent(timeFrame = ALL_TIME, modifier = modifier)
    }
}

@Preview
@Composable
fun PreviewTracksScreen() {
    TracksScreen()
}
