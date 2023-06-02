package com.efedaniel.spotifystats.ui.scene.topartist

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ChipDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.efedaniel.spotifystats.core.ScreenState
import com.efedaniel.spotifystats.domain.model.TimeRange
import com.efedaniel.spotifystats.domain.model.TopArtist
import com.efedaniel.spotifystats.ui.proton.components.image.ProtonImage
import com.efedaniel.spotifystats.ui.proton.components.text.ProtonText
import com.efedaniel.spotifystats.ui.proton.theme.ProtonTheme
import com.efedaniel.spotifystats.ui.proton.tokens.dimension.ProtonDimension

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterialApi::class,
    ExperimentalAnimationApi::class
)
@Composable
fun TopArtistScreen(
    modifier: Modifier = Modifier,
    viewModel: TopArtistViewModel = hiltViewModel(),
    timeRanges: List<TimeRange> = TimeRange.values().toList(), // Fixme: Change to ImmutableList
) {
    LaunchedEffect(Unit) {
        viewModel.fetchTopArtists()
    }

    Column(
        modifier = modifier
    ) {
        FlowRow(
            modifier = Modifier.padding(horizontal = ProtonDimension.Spacing8),
            horizontalArrangement = Arrangement.spacedBy(ProtonDimension.Spacing8),
            verticalAlignment = Alignment.CenterVertically
        ) {
            timeRanges.forEach { timeRange ->
                FilterChip(
                    selected = viewModel.state.timeRange == timeRange,
                    onClick = { viewModel.onNewEvent(TopArtistEvent.TimeRangeChange(timeRange)) },
                    border = BorderStroke(
                        width = ProtonDimension.Stroke1,
                        color = ProtonTheme.colors.white.copy(alpha = 0.1f)
                    ),
                    colors = ChipDefaults.filterChipColors(
                        backgroundColor = ProtonTheme.colors.black,
                        selectedBackgroundColor = ProtonTheme.colors.green,
                    ),
                    content = { ProtonText(text = stringResource(timeRange.title)) }
                )
            }
        }

        AnimatedContent(
            targetState = viewModel.state.screenState,
            transitionSpec = { fadeIn() with fadeOut() },
            label = "Screen State"
        ) {
            when(it) {
                ScreenState.LOADING -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = ProtonTheme.colors.white)
                    }
                }
                ScreenState.SUCCESS -> {
                    TopArtistContent(
                        state = viewModel.state,
                        onNewEvent = viewModel::onNewEvent,
                        modifier = Modifier.weight(1.0f)
                    )
                }
                ScreenState.ERROR -> {

                }
            }
        }
    }
}

@Composable
private fun TopArtistContent(
    state: TopArtistUiState,
    onNewEvent: (TopArtistEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 200.dp),
            modifier = modifier
        ) {
            items(
                items = state.artists
            ) { artist ->
                TopArtistCard(
                    artist = artist,
                    modifier = Modifier.padding(ProtonDimension.Spacing8),
                    onArtistClicked = { }
                )
            }
        }
    }
}

@Composable
private fun TopArtistCard(
    artist: TopArtist,
    onArtistClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    var height by remember { mutableStateOf(0) }

    Card(
        modifier = modifier
            .clip(RoundedCornerShape(ProtonDimension.Corner8))
            .requiredHeight(200.dp)
            .clickable(onClick = onArtistClicked),
        elevation = ProtonDimension.Spacing16
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned { height = it.size.height }
        ) {
            ProtonImage(
                url = artist.imageUrl.orEmpty(),
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                ProtonTheme.colors.transparent,
                                ProtonTheme.colors.black,
                            ),
                            startY = height * 0.5f,
                            endY = height * 1.0f,
                        )
                    )
            )
            ProtonText(
                text = artist.name,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(all = ProtonDimension.Spacing8)
            )
        }
    }

}
