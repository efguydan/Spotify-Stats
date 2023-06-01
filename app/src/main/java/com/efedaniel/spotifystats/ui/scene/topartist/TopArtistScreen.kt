package com.efedaniel.spotifystats.ui.scene.topartist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.efedaniel.spotifystats.domain.model.TimeRange
import com.efedaniel.spotifystats.domain.model.TopArtist
import com.efedaniel.spotifystats.ui.proton.components.text.ProtonText
import com.efedaniel.spotifystats.ui.proton.theme.ProtonTheme
import com.efedaniel.spotifystats.ui.proton.tokens.dimension.ProtonDimension

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterialApi::class)
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
            modifier = Modifier.padding(ProtonDimension.Spacing8),
            horizontalArrangement = Arrangement.spacedBy(ProtonDimension.Spacing8),
            verticalAlignment = Alignment.CenterVertically
        ) {
            timeRanges.forEach { timeRange ->
                FilterChip(
                    selected = viewModel.state.timeRange == timeRange,
                    onClick = { viewModel.onTimeRangeSelected(timeRange) },
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
}

@Composable
private fun TopArtistCard(
    artist: TopArtist,
    modifier: Modifier = Modifier
) {
    ProtonText(text = artist.name)
}
