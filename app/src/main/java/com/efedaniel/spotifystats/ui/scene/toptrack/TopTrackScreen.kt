package com.efedaniel.spotifystats.ui.scene.toptrack

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.efedaniel.spotifystats.ui.proton.components.text.ProtonText

@Composable
fun TopTrackScreen(
    modifier: Modifier = Modifier,
    viewModel: TopTrackViewModel = hiltViewModel(),
) {
    ProtonText(text = "Top Tracks Screen")
}
