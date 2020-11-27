package com.efedaniel.spotifystats.ui.commons.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun NetworkImage(url: String, modifier: Modifier = Modifier) {
    CoilImage(
        data = url,
        contentScale = ContentScale.Crop,
        fadeIn = true,
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
    )
}
