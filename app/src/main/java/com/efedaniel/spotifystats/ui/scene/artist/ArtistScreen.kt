package com.efedaniel.spotifystats.ui.scene.artist

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.efedaniel.spotifystats.core.ScreenState
import com.efedaniel.spotifystats.ui.proton.components.text.ProtonText
import com.efedaniel.spotifystats.ui.proton.patterns.loader.ProtonLoader

@OptIn(
    ExperimentalAnimationApi::class
)
@Composable
fun ArtistScreen(
    id: String?,
    modifier: Modifier = Modifier,
    viewModel: ArtistViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = id) { viewModel.fetchArtist(id) }

    AnimatedContent(
        targetState = viewModel.state.screenState,
        transitionSpec = { fadeIn() with fadeOut() },
        label = "Screen State"
    ) {
        when (it) {
            ScreenState.LOADING -> ProtonLoader(modifier)
            ScreenState.SUCCESS -> ArtistContent(viewModel.state, modifier)
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
        ProtonText(text = state.artist?.name.toString())
    }
}