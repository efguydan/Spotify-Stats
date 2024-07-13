package com.efedaniel.spotifystats.ui.scene.artist

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
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
import com.efedaniel.spotifystats.ui.proton.tokens.color.ProtonColor.Neutral8
import com.efedaniel.spotifystats.ui.proton.tokens.color.ProtonColor.Red
import com.efedaniel.spotifystats.ui.proton.tokens.dimension.ProtonDimension
import kotlin.math.max
import kotlin.math.min




private val BottomBarHeight = 56.dp
private val TitleHeight = 128.dp
private val GradientScroll = 180.dp
private val ImageOverlap = 115.dp
private val MinTitleOffset = 56.dp
private val MinImageOffset = 12.dp
private val MaxTitleOffset = ImageOverlap + MinTitleOffset + GradientScroll
private val ExpandedImageSize = 300.dp
private val CollapsedImageSize = 150.dp
private val HzPadding = Modifier.padding(horizontal = 24.dp)



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
        ArtistSection(artist = state.artist, modifier = Modifier, upPress = {})
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
    scrollState: ScrollState = rememberScrollState(0),
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
        progress = min((scrollState.value) / 600f, 1f),
        modifier = Modifier,
    ) {

        ProtonImage(
            url = artist.imageUrl.orEmpty(),
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .layoutId("artist_picture")
                .fillMaxWidth()
                .aspectRatio(1.0f)
        )

        ProtonText(
            modifier = Modifier
                .padding(start = ProtonDimension.Spacing8)
                .layoutId("artist_name"),
            text = artist.name,
            style = ProtonTheme.typography.headlineMedium,
            color = Color.White
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        Spacer(Modifier.height(400.dp))
        repeat(5) {
            Text(
                text = LoremIpsum(222).values.first(),
                modifier = Modifier
                    .background(Color.White)
                    .padding(16.dp)
            )
        }
    }
}


@Composable
fun ArtistSection(
    artist: Artist,
    modifier: Modifier,
    upPress:() -> Unit
)  {

    Box(Modifier.fillMaxSize()) {

        val scroll = rememberScrollState(0)

        Image(artist.imageUrl) { scroll.value }

    }

}


@Composable
fun Header() {
    Spacer(
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth()
            .background(Brush.horizontalGradient(listOf(Color.Black, Color.Green)))
    )
}

@Composable
fun Up(upPress: () -> Unit) {
    IconButton(
        onClick = upPress,
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .size(36.dp)
            .background(
                color = Neutral8.copy(alpha = 0.32f),
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = Icons.Outlined.KeyboardArrowLeft,
            tint = ProtonTheme.colors.transparent,
            contentDescription = "nav icon"
        )
    }
}

@Composable
private fun Body(
    artist: Artist,
    scroll: ScrollState
) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(MinTitleOffset)
        )
        Column(
            modifier = Modifier.verticalScroll(scroll)
        ) {
            Spacer(Modifier.height(GradientScroll))
            Surface(Modifier.fillMaxWidth()) {
                Column {
                    Spacer(Modifier.height(ImageOverlap))
                    Spacer(Modifier.height(TitleHeight))

                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "I am good well",
                        style = MaterialTheme.typography.overline,
                        color = ProtonTheme.colors.Neutral1,
                        modifier = HzPadding
                    )
                    Spacer(Modifier.height(16.dp))
                    var seeMore by remember { mutableStateOf(true) }
                    Text(
                        text = "i AM GOOO",
                        style = MaterialTheme.typography.body1,
                        color = ProtonTheme.colors.Neutral0,
                        maxLines = if (seeMore) 5 else Int.MAX_VALUE,
                        overflow = TextOverflow.Ellipsis,
                        modifier = HzPadding
                    )
                    val textButton = if (seeMore) {
                        "See more"
                    } else {
                        "See less"
                    }
                    Text(
                        text = textButton,
                        style = MaterialTheme.typography.button,
                        textAlign = TextAlign.Center,
                        color = ProtonTheme.colors.transparent,
                        modifier = Modifier
                            .heightIn(20.dp)
                            .fillMaxWidth()
                            .padding(top = 15.dp)
                            .clickable {
                                seeMore = !seeMore
                            }
                    )
                    Spacer(Modifier.height(40.dp))
                    Text(
                        text = "ingredient",
                        style = MaterialTheme.typography.overline,
                        color = ProtonTheme.colors.transparent,
                        modifier = HzPadding
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "ingredients_list",
                        style = MaterialTheme.typography.body1,
                        color = ProtonTheme.colors.transparent,
                        modifier = HzPadding
                    )

                    Spacer(Modifier.height(16.dp))
                    //JetsnackDivider()


                    Spacer(
                        modifier = Modifier
                            .padding(bottom = BottomBarHeight)
                            .navigationBarsPadding()
                            .height(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun Title(artist: Artist, scrollProvider: () -> Int) {
    val maxOffset = with(LocalDensity.current) { MaxTitleOffset.toPx() }
    val minOffset = with(LocalDensity.current) { MinTitleOffset.toPx() }

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .heightIn(min = TitleHeight)
            .statusBarsPadding()
            .offset {
                val scroll = scrollProvider()
                val offset = (maxOffset - scroll)
                    .coerceAtLeast(minOffset)
                IntOffset(x = 0, y = offset.toInt())
            }
            .background(color = ProtonTheme.colors.white)
    ) {
        Spacer(Modifier.height(16.dp))
        Text(
            text = artist.name,
            style = MaterialTheme.typography.h4,
            color = ProtonTheme.colors.black,
            modifier = HzPadding
        )
        Text(
            text = artist.popularity.toString(),
            style = MaterialTheme.typography.subtitle2,
            fontSize = 20.sp,
            color =  ProtonTheme.colors.transparent,
            modifier = HzPadding
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = artist.genres.toString(),
            style = MaterialTheme.typography.h6,
            color = ProtonTheme.colors.Neutral5,
            modifier = HzPadding
        )

        Spacer(Modifier.height(8.dp))
        //JetsnackDivider()
    }
}

@Composable
private fun Image(
    url: String?,
    scrollProvider: () -> Int
) {
    val collapseRange = with(LocalDensity.current) { (MaxTitleOffset - MinTitleOffset).toPx() }
    val collapseFractionProvider = {
        (scrollProvider() / collapseRange).coerceIn(0f, 1f)
    }

    CollapsingImageLayout(
        collapseFractionProvider = collapseFractionProvider,
        modifier = HzPadding.statusBarsPadding()
    ) {
        if (url != null) {
            ProtonImage(
                url = url,
                contentDescription = "",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}



@Composable
private fun CollapsingImageLayout(
    collapseFractionProvider: () -> Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        check(measurables.size == 1)

        val collapseFraction = collapseFractionProvider()

        val imageMaxSize = min(ExpandedImageSize.roundToPx(), constraints.maxWidth)
        val imageMinSize = max(CollapsedImageSize.roundToPx(), constraints.minWidth)
        val imageWidth = lerp(imageMaxSize.dp  , imageMinSize.dp, collapseFraction)
       // val imagePlaceable = measurables[0].measure(Constraints.fixed(imageWidth, imageWidth))

        val imageY = lerp(MinTitleOffset, MinImageOffset, collapseFraction).roundToPx()
        val imageX = lerp(
            (constraints.maxWidth.dp - imageWidth) / 2, // centered when expanded
            constraints.maxWidth.dp - imageWidth, // right aligned when collapsed
            collapseFraction
        )
        layout(
            width = constraints.maxWidth,
            height = imageY.toInt()
                    //+ imageWidth.
        ) {
          //  imagePlaceable.placeRelative(imageX, imageY)
        }
    }
}



