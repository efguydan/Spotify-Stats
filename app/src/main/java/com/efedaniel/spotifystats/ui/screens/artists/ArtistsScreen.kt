package com.efedaniel.spotifystats.ui.screens.artists

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.efedaniel.spotifystats.R
import com.efedaniel.spotifystats.ui.commons.components.SpotifyStatsAppBar
import com.efedaniel.spotifystats.ui.commons.layouts.TabLayout
import com.efedaniel.spotifystats.ui.theme.SpotifyStatsTheme
import com.efedaniel.spotifystats.utils.StatsTimeFrame

@Composable
fun ArtistsScreen() {
    SpotifyStatsTheme {
        Scaffold(
            // TODO Fix elevation of this app bar too
            topBar = { SpotifyStatsAppBar(title = stringResource(R.string.artists)) }
        ) {
            val (selectedIndex, updateSelectedIndex) = remember { mutableStateOf(StatsTimeFrame.WEEKS.ordinal) }
            Column(modifier = Modifier.fillMaxSize()) {
                TabLayout(
                    selectedIndex = selectedIndex,
                    updateSelectedIndex = updateSelectedIndex,
                    tabsName = remember { StatsTimeFrame.values().map { it.title } },
                    onClick = {
                        when (it) {
                            StatsTimeFrame.WEEKS.title -> updateSelectedIndex(StatsTimeFrame.WEEKS.ordinal)
                            StatsTimeFrame.MONTHS.title -> updateSelectedIndex(StatsTimeFrame.MONTHS.ordinal)
                            StatsTimeFrame.ALL_TIME.title -> updateSelectedIndex(StatsTimeFrame.ALL_TIME.ordinal)
                        }
                    }
                )
                // TracksPagerContent(selectedIndex, updateSelectedIndex, modifier = Modifier.weight(1f))
            }
        }
    }
}
