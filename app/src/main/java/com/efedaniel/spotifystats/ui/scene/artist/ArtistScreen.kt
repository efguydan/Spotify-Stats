package com.efedaniel.spotifystats.ui.scene.artist

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
        ProtonImage(
            url = artist.imageUrl.orEmpty(),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.0f)
        )
        Spacer(modifier = Modifier.height(ProtonDimension.Spacing4))
        ProtonText(
            text = artist.name,
            style = ProtonTheme.typography.headlineMedium,
            modifier = Modifier.padding(start = ProtonDimension.Spacing8)
        )
    }

    Spacer(modifier = Modifier.height(ProtonDimension.Spacing4))
    LazyRow {
        items(artist.genres) { item ->
            Chip(text = item)
        }
    }
}
