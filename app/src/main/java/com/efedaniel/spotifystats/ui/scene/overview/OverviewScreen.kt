package com.efedaniel.spotifystats.ui.scene.overview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.efedaniel.spotifystats.ui.proton.components.text.ProtonText

@Composable
fun OverviewScreen(
    modifier: Modifier = Modifier
) {
    ProtonText(text = "Overview Screen", modifier.fillMaxSize())
}