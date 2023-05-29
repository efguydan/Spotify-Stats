package com.efedaniel.spotifystats.ui.proton.components.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ProtonImage(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    contentDescription: String = "Image",
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )
}