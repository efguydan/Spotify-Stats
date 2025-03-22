package com.efedaniel.spotifystats.ui.scene.artist

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.efedaniel.spotifystats.core.ScreenState
import com.efedaniel.spotifystats.domain.model.Artist
import com.efedaniel.spotifystats.ui.proton.components.chips.Chip
import com.efedaniel.spotifystats.ui.proton.components.image.ProtonImage
import com.efedaniel.spotifystats.ui.proton.components.text.ProtonText
import com.efedaniel.spotifystats.ui.proton.patterns.loader.ProtonLoader
import com.efedaniel.spotifystats.ui.proton.theme.ProtonTheme
import com.efedaniel.spotifystats.ui.proton.tokens.dimension.ProtonDimension
import timber.log.Timber

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

    var height by remember { mutableStateOf(0) }

    Column(modifier = modifier) {
        Box(modifier = Modifier.requiredHeight(height.dp)) {
            ProtonImage(
                url = artist.imageUrl.orEmpty(),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.0f)
                    .onGloballyPositioned { layoutCoordinates ->
                        height = layoutCoordinates.size.height/2 }
            )

            //It returns the image height as 720dp when it is actually 360dp
            //Log.d("Artist", height.toString())
            Box(modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(
                    colors = listOf(
                        ProtonTheme.colors.transparent,
                        ProtonTheme.colors.black,
                    ),
                )
                )
            ) {
                ProtonText(
                    text = artist.name,
                    style = ProtonTheme.typography.headlineLarge,
                    modifier = Modifier.padding(start = ProtonDimension.Spacing8)
                        .align(Alignment.BottomStart)
                )
            }
        }
        Spacer(modifier = Modifier.height(ProtonDimension.Spacing24))
        ProtonText(
            text = "Genres",
            style = ProtonTheme.typography.titleMedium,
            modifier = Modifier.padding(start = ProtonDimension.Spacing8)
        )
        LazyRow {
            items(artist.genres) { item ->
                Chip(text = item)
            }
        }
    }
}
