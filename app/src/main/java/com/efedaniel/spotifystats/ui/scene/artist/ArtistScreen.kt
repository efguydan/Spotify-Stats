package com.efedaniel.spotifystats.ui.scene.artist

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.hilt.navigation.compose.hiltViewModel
import com.efedaniel.spotifystats.R
import com.efedaniel.spotifystats.core.ScreenState
import com.efedaniel.spotifystats.domain.model.Artist
import com.efedaniel.spotifystats.ui.proton.components.chips.Chip
import com.efedaniel.spotifystats.ui.proton.components.image.ProtonImage
import com.efedaniel.spotifystats.ui.proton.components.text.ProtonText
import com.efedaniel.spotifystats.ui.proton.patterns.loader.ProtonLoader
import com.efedaniel.spotifystats.ui.proton.theme.ProtonTheme
import com.efedaniel.spotifystats.ui.proton.tokens.dimension.ProtonDimension


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
       // ArtistSection(artist = state.artist)
        ArtistSection(artist = state.artist, progress = 50f)
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
    LazyRow() {
        items(artist.genres) { item ->
            Chip(text = item)
        }
    }
}

@OptIn(ExperimentalMotionApi::class)
@Composable
fun ArtistSection(
    artist: Artist,
    modifier: Modifier = Modifier,
    progress: Float,
    scrollState: ScrollState = rememberScrollState(),
) {

    val context = LocalContext.current

    // To include raw file, the JSON5 script file
    val motionScene = remember {
        context.resources.openRawResource(R.raw.artist_motion_scene)
            .readBytes()
            .decodeToString()   //readBytes -> cuz we want motionScene in a String format
    }

    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier
    ) {

        /**
         * bg-image
         **/
        ProtonImage(
            url = artist.imageUrl.orEmpty(),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .layoutId(" artist_picture")
                .fillMaxWidth()
                .aspectRatio(1.0f)
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = size.height / 3,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.Multiply)
                    }
                },
        )



        ProtonText(
            text = artist.name,
            style = ProtonTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(start = ProtonDimension.Spacing8)
                .layoutId("artist_name")
                .zIndex(1f),
        )






    }

}
