package com.efedaniel.spotifystats.ui.screens.tracks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.efedaniel.spotifystats.R
import com.efedaniel.spotifystats.ui.commons.components.SpotifyStatsAppBar
import com.efedaniel.spotifystats.ui.screens.activity.TracksTimeFrame
import com.efedaniel.spotifystats.ui.screens.activity.TracksTimeFrame.ALL_TIME
import com.efedaniel.spotifystats.ui.screens.activity.TracksTimeFrame.MONTHS
import com.efedaniel.spotifystats.ui.screens.activity.TracksTimeFrame.WEEKS
import com.efedaniel.spotifystats.ui.theme.SpotifyStatsTheme

@Composable
fun TracksScreen() {
    SpotifyStatsTheme {
        Scaffold(
            topBar = { SpotifyStatsAppBar(title = stringResource(R.string.tracks), elevation = 0.dp) }
        ) {
            val (selectedIndex, updateSelectedIndex) = remember { mutableStateOf(WEEKS.ordinal) }
            Column(modifier = Modifier.fillMaxSize()) {
                TabLayout(selectedIndex, updateSelectedIndex)
                ViewPagerContent(selectedIndex, updateSelectedIndex, modifier = Modifier.weight(1f))
            }
        }
    }
}

// TODO Move these to appropriate screens later

@Composable
fun TabLayout(
    selectedIndex: Int,
    updateSelectedIndex: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabsName = remember { TracksTimeFrame.values().map { it.title } }

    // TODO Decide whether this or ScrollableTabRow should be used later
    TabRow(selectedTabIndex = selectedIndex, modifier = modifier) {
        tabsName.forEachIndexed { index, title ->
            Tab(
                selected = selectedIndex == index,
                onClick = {
                    when (title) {
                        WEEKS.title -> updateSelectedIndex(WEEKS.ordinal)
                        MONTHS.title -> updateSelectedIndex(MONTHS.ordinal)
                        ALL_TIME.title -> updateSelectedIndex(ALL_TIME.ordinal)
                    }
                },
                text = { Text(title.toUpperCase()) }
            )
        }
    }
}

@Composable
fun ViewPagerContent(
    selectedIndex: Int,
    updateSelectedIndex: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    // TODO Come later to update selected Index based on left and right scrolls
    when (selectedIndex) {
        WEEKS.ordinal -> TracksListContent(timeFrame = WEEKS)
        MONTHS.ordinal -> TracksListContent(timeFrame = MONTHS)
        ALL_TIME.ordinal -> TracksListContent(timeFrame = ALL_TIME)
    }
}

@Composable
fun TracksListContent(
    modifier: Modifier = Modifier,
    timeFrame: TracksTimeFrame
) {
    Text(timeFrame.title)
}

@Preview
@Composable
fun PreviewTracksScreen() {
    TracksScreen()
}

@Preview
@Composable
fun PreviewTracksListContent() {
    SpotifyStatsTheme { TracksListContent(timeFrame = WEEKS) }
}
