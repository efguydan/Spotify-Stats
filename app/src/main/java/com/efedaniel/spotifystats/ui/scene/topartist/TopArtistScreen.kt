package com.efedaniel.spotifystats.ui.scene.topartist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.efedaniel.spotifystats.domain.model.TimeRange
import com.efedaniel.spotifystats.domain.model.TopArtist
import com.efedaniel.spotifystats.ui.proton.components.text.ProtonText
import com.efedaniel.spotifystats.ui.proton.theme.ProtonTheme
import com.efedaniel.spotifystats.ui.proton.tokens.dimension.ProtonDimension
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopArtistScreen(
    modifier: Modifier = Modifier,
    timeRanges: List<TimeRange> = TimeRange.values().toList(), // Fixme: Change to ImmutableList
    pagerState: PagerState = rememberPagerState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) {
    Column(
        modifier = modifier
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                        .height(ProtonDimension.Stroke1)
                        .background(color = ProtonTheme.colors.green)
                )
            }
        ) {
            timeRanges.forEachIndexed { index, timeRange ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    text = { ProtonText(text = stringResource(id = timeRange.title)) },
                    selectedContentColor = ProtonTheme.colors.green,
                    unselectedContentColor = ProtonTheme.colors.white,
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            pageCount = TimeRange.values().size,
            modifier = Modifier.weight(1.0f)
        ) { index ->
            TopArtistsGrid(
                timeRange = timeRanges[index],
                modifier = Modifier.fillMaxSize()
            )
        }
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

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 200.dp),
        modifier = modifier
    ) {
        items(
            items = viewModel.state.artists
        ) { artist ->
            TopArtistCard(artist = artist)
        }
    }
}

@Composable
private fun TopArtistCard(
    artist: TopArtist,
    modifier: Modifier = Modifier
) {
    ProtonText(text = artist.name)
}
