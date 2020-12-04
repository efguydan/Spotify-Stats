package com.efedaniel.spotifystats.ui.commons.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SpotifyStatsAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    elevation: Dp = 4.dp
) {
    TopAppBar(
        title = { Text(text = title) },
        elevation = elevation,
        modifier = modifier
    )
}
