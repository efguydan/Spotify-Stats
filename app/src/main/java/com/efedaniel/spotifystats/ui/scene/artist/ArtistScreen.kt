package com.efedaniel.spotifystats.ui.scene.artist

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.efedaniel.spotifystats.ui.proton.components.text.ProtonText

@Composable
fun ArtistScreen(
    modifier: Modifier = Modifier,
    viewModel: ArtistViewModel = hiltViewModel(),
) {
    ProtonText(text = "Okay now what?")
}
