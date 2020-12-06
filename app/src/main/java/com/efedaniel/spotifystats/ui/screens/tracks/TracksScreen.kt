package com.efedaniel.spotifystats.ui.screens.tracks

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.efedaniel.spotifystats.R
import com.efedaniel.spotifystats.ui.commons.components.SpotifyStatsAppBar
import com.efedaniel.spotifystats.ui.commons.components.TrackCard
import com.efedaniel.spotifystats.ui.commons.layouts.DynamicVerticalGrid
import com.efedaniel.spotifystats.ui.screens.tracks.TracksTimeFrame.ALL_TIME
import com.efedaniel.spotifystats.ui.screens.tracks.TracksTimeFrame.MONTHS
import com.efedaniel.spotifystats.ui.screens.tracks.TracksTimeFrame.WEEKS
import com.efedaniel.spotifystats.ui.theme.SpotifyStatsTheme
import com.efedaniel.spotifystats.utils.sePaddingModifier
import com.efedaniel.spotifystats.utils.toPx

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
        WEEKS.ordinal -> TracksListContent(timeFrame = WEEKS, modifier = modifier)
        MONTHS.ordinal -> TracksListContent(timeFrame = MONTHS, modifier = modifier)
        ALL_TIME.ordinal -> TracksListContent(timeFrame = ALL_TIME, modifier = modifier)
    }
}

@Composable
fun TracksListContent(
    modifier: Modifier = Modifier,
    timeFrame: TracksTimeFrame
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

@Composable
fun TopTrackCard(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        TrackCard()
        Text(
            text = "1",
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 8.dp, top = 8.dp)
        )
    }
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
