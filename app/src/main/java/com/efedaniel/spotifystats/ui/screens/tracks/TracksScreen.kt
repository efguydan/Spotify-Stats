package com.efedaniel.spotifystats.ui.screens.tracks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
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
            topBar = { SpotifyStatsAppBar(title = stringResource(R.string.tracks)) }
        ) {
            val (selectedIndex, updateSelectedIndex) = remember { mutableStateOf(WEEKS.ordinal) }
            Column(modifier = Modifier.fillMaxSize()) {
                TabLayout(selectedIndex, updateSelectedIndex)
                ViewPagerContent(selectedIndex, updateSelectedIndex, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun TabLayout(
    selectedIndex: Int,
    updateSelectedIndex: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabsName = remember { TracksTimeFrame.values().map { it.name } }

    Column {
        // TODO Decide whether this or ScrollableTabRow should be used later
        TabRow(selectedTabIndex = selectedIndex) {
            tabsName.forEachIndexed { index, title ->
                Tab(
                    selected = selectedIndex == index,
                    onClick = {
                        when (title) {
                            WEEKS.name -> updateSelectedIndex(WEEKS.ordinal)
                            MONTHS.name -> updateSelectedIndex(MONTHS.ordinal)
                            ALL_TIME.name -> updateSelectedIndex(ALL_TIME.ordinal)
                        }
                    },
                    text = { Text(title) }
                )
            }
        }
    }
}

@Composable
fun ViewPagerContent(
    selectedIndex: Int,
    updateSelectedIndex: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    when (selectedIndex) {
        WEEKS.ordinal -> WeeksTracksContent(modifier)
        MONTHS.ordinal -> MonthsTracksContent(modifier)
        ALL_TIME.ordinal -> AllTimeTracksContent(modifier)
    }
}

@Composable
fun WeeksTracksContent(
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier.background(Color.Blue)) {
    }
}

@Composable
fun MonthsTracksContent(
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier.background(Color.Red)) {
    }
}

@Composable
fun AllTimeTracksContent(
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier.background(Color.Yellow)) {
    }
}
