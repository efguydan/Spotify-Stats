package com.efedaniel.spotifystats.ui.scene.artist

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.efedaniel.spotifystats.core.ScreenState
import com.efedaniel.spotifystats.domain.model.Artist
import com.efedaniel.spotifystats.ui.proton.components.chips.Chip
import com.efedaniel.spotifystats.ui.proton.components.image.ProtonImage
import com.efedaniel.spotifystats.ui.proton.components.text.ProtonText
import com.efedaniel.spotifystats.ui.proton.patterns.loader.ProtonLoader
import com.efedaniel.spotifystats.ui.proton.theme.ProtonTheme
import com.efedaniel.spotifystats.ui.proton.tokens.dimension.ProtonDimension

@Composable
fun ArtistScreen(
    id: String?,
    modifier: Modifier = Modifier,
    viewModel: ArtistViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = id) {
        viewModel.fetchArtistInformation(id)
    }

    AnimatedContent(
        targetState = viewModel.state.screenState,
        transitionSpec = { fadeIn() togetherWith  fadeOut() },
        label = "Screen State"
    ) {
        when (it) {
            ScreenState.LOADING -> ProtonLoader(modifier.fillMaxSize())
            ScreenState.SUCCESS -> ArtistContent(viewModel.state, modifier.fillMaxSize())
            ScreenState.ERROR -> {
                // TODO: Add Error Component
                ProtonText(text = "There was an error")
            }
        }
    }
}

@Composable
fun ArtistContent(
    state: ArtistUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        ArtistSection(artist = state.artist)
    }
}

@Composable
fun ArtistSection(
    artist: Artist,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {

        Box(modifier = modifier) {
            ProtonImage(
                url = artist.imageUrl.orEmpty(),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.0f)
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

            ProtonText(
                text = artist.name,
                style = ProtonTheme.typography.displayLarge,
                modifier = Modifier.padding(start = ProtonDimension.Spacing16)
                    .align(Alignment.BottomStart)
            )
        }



        Spacer(modifier = Modifier.height(ProtonDimension.Spacing16))

        ProtonText(
            text = "Genres",
            style = ProtonTheme.typography.titleMedium,
            modifier = Modifier.padding(start = ProtonDimension.Spacing16)
        )
        LazyRow(modifier = Modifier.padding(start = ProtonDimension.Spacing16)) {
            items(artist.genres) { item ->
                Chip(text = item)
            }
        }
    }
}

@Preview
@Composable
fun ArtistScreenPreview() {


}
