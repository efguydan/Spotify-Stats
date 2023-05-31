package com.efedaniel.spotifystats.ui.scene.artist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.efedaniel.spotifystats.domain.model.TimeRange
import com.efedaniel.spotifystats.ui.proton.components.text.ProtonText

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopArtistScreen(
    modifier: Modifier = Modifier,
    timeRanges: List<TimeRange> = TimeRange.values().toList(), // Fixme: Change to ImmutableList
) {
    HorizontalPager(
        pageCount = TimeRange.values().size,
        modifier = modifier.fillMaxSize()
    ) { index ->
        TopArtistsGrid(
            timeRange = timeRanges[index],
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun TopArtistsGrid(
    timeRange: TimeRange,
    modifier: Modifier = Modifier,
    viewModel: TopArtistViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.fetchTopArtists(timeRange)
    }

    ProtonText(
        text = timeRange.value,
        modifier = modifier
    )
}