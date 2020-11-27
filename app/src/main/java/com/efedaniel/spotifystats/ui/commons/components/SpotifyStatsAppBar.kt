package com.efedaniel.spotifystats.ui.commons.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SpotifyStatsAppBar(
    modifier: Modifier = Modifier,
    title: String = ""
) {
    TopAppBar(
        title = { Text(text = title) },
        elevation = 4.dp,
        modifier = modifier
    )
}
