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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ChipDefaults
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.efedaniel.spotifystats.core.ScreenState
import com.efedaniel.spotifystats.domain.model.TimeRange
import com.efedaniel.spotifystats.domain.model.TopArtist
import com.efedaniel.spotifystats.ui.proton.components.image.ProtonImage
import com.efedaniel.spotifystats.ui.proton.components.text.ProtonText
import com.efedaniel.spotifystats.ui.proton.patterns.loader.ProtonLoader
import com.efedaniel.spotifystats.ui.proton.theme.ProtonTheme
import com.efedaniel.spotifystats.ui.proton.tokens.dimension.ProtonDimension
import com.efedaniel.spotifystats.ui.scene.topartist.TopArtistEvent.ArtistClick
import kotlinx.coroutines.flow.collectLatest

@OptIn(
    ExperimentalLayoutApi::class,
    ExperimentalMaterialApi::class,
    ExperimentalAnimationApi::class
)
@Composable
fun TopArtistScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: TopArtistViewModel = hiltViewModel(),
    timeRanges: List<TimeRange> = TimeRange.values().toList(), // Fixme: Change to ImmutableList
) {
    LaunchedEffect(Unit) {
        viewModel.destinations.collectLatest {
            viewModel.navigator.navigate(it, navController)
        }
    }

    Column(
        modifier = modifier
    ) {
        FlowRow(
            modifier = Modifier.padding(horizontal = ProtonDimension.Spacing8),
            horizontalArrangement = Arrangement.spacedBy(ProtonDimension.Spacing8),
            verticalArrangement = Arrangement.Center,
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
                    // FixMe: Replace with shimmer effect loading
                    ProtonLoader()
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
            columns = GridCells.Adaptive(minSize = ProtonDimension.ComponentSize200),
            modifier = modifier
        ) {
            items(
                items = state.artists
            ) { artist ->
                TopArtistCard(
                    artist = artist,
                    modifier = Modifier.padding(ProtonDimension.Spacing8),
                    onArtistClicked = { onNewEvent(ArtistClick(artist.id)) },
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
            .requiredHeight(ProtonDimension.ComponentSize200)
            .clickable(onClick = onArtistClicked),
        elevation = ProtonDimension.Spacing16,
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = ProtonDimension.Spacing8)
                    .align(Alignment.BottomStart),
                verticalAlignment = Alignment.Bottom
            ) {
                ProtonText(
                    text = artist.name,
                    modifier = Modifier
                        .weight(1.0f),
                )
                ProtonText(
                    text = artist.position.toString(),
                    style = ProtonTheme.typography.titleLarge
                )
            }
        }
    }
}
