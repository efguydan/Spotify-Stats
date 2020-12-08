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
import com.efedaniel.spotifystats.utils.StatsTimeFrame.ALL_TIME
import com.efedaniel.spotifystats.utils.StatsTimeFrame.MONTHS
import com.efedaniel.spotifystats.utils.StatsTimeFrame.WEEKS

@Composable
fun ArtistsScreen() {
    SpotifyStatsTheme {
        Scaffold(
            // TODO Fix elevation of this app bar too
            topBar = { SpotifyStatsAppBar(title = stringResource(R.string.artists)) }
        ) {
            val (selectedIndex, updateSelectedIndex) = remember { mutableStateOf(WEEKS.ordinal) }
            Column(modifier = Modifier.fillMaxSize()) {
                TabLayout(
                    selectedIndex = selectedIndex,
                    updateSelectedIndex = updateSelectedIndex,
                    tabsName = remember { StatsTimeFrame.values().map { it.title } },
                    onClick = {
                        when (it) {
                            WEEKS.title -> updateSelectedIndex(WEEKS.ordinal)
                            MONTHS.title -> updateSelectedIndex(MONTHS.ordinal)
                            ALL_TIME.title -> updateSelectedIndex(ALL_TIME.ordinal)
                        }
                    }
                )
                ArtistsPagerContent(selectedIndex, updateSelectedIndex, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun ArtistsPagerContent(
    selectedIndex: Int,
    updateSelectedIndex: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    when (selectedIndex) {
        WEEKS.ordinal -> ArtistListContent(timeFrame = WEEKS, modifier = modifier)
        MONTHS.ordinal -> ArtistListContent(timeFrame = MONTHS, modifier = modifier)
        ALL_TIME.ordinal -> ArtistListContent(timeFrame = ALL_TIME, modifier = modifier)
    }
}
