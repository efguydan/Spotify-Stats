package com.efedaniel.spotifystats.ui.scene.overview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.efedaniel.spotifystats.ui.proton.components.text.ProtonText

@Composable
fun OverviewScreen(
    modifier: Modifier = Modifier,
    viewModel: OverviewViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.fetchCurrentPlayback()
    }
    ProtonText(text = "Overview Screen", modifier.fillMaxSize())
}