package com.efedaniel.spotifystats.ui.commons.layouts

import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.efedaniel.spotifystats.ui.screens.tracks.TracksTimeFrame

@Composable
fun TabLayout(
    selectedIndex: Int = 0,
    updateSelectedIndex: (Int) -> Unit = { },
    tabsName: List<String> = emptyList<String>(),
    modifier: Modifier = Modifier
) {
    // TODO Decide whether this or ScrollableTabRow should be used later
    TabRow(selectedTabIndex = selectedIndex, modifier = modifier) {
        tabsName.forEachIndexed { index, title ->
            Tab(
                selected = selectedIndex == index,
                onClick = {
                    when (title) {
                        TracksTimeFrame.WEEKS.title -> updateSelectedIndex(TracksTimeFrame.WEEKS.ordinal)
                        TracksTimeFrame.MONTHS.title -> updateSelectedIndex(TracksTimeFrame.MONTHS.ordinal)
                        TracksTimeFrame.ALL_TIME.title -> updateSelectedIndex(TracksTimeFrame.ALL_TIME.ordinal)
                    }
                },
                text = { Text(title.toUpperCase()) }
            )
        }
    }
}
