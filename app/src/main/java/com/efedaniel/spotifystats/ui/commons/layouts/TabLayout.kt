package com.efedaniel.spotifystats.ui.commons.layouts

import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TabLayout(
    selectedIndex: Int = 0,
    updateSelectedIndex: (Int) -> Unit = { },
    tabsName: List<String> = emptyList<String>(),
    onClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
) {
    // TODO Decide whether this or ScrollableTabRow should be used later
    TabRow(selectedTabIndex = selectedIndex, modifier = modifier) {
        tabsName.forEachIndexed { index, title ->
            Tab(
                selected = selectedIndex == index,
                onClick = { onClick(title) },
                text = { Text(title.toUpperCase()) }
            )
        }
    }
}
