package com.efedaniel.spotifystats.ui.proton.patterns.loader

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.efedaniel.spotifystats.ui.proton.theme.ProtonTheme
import com.efedaniel.spotifystats.utility.extensions.conditional

@Composable
fun ProtonLoader(
    modifier: Modifier = Modifier,
    isFullScreen: Boolean = true,
) {
    Box(
        modifier = Modifier
            .conditional(isFullScreen) { fillMaxSize() },
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(color = ProtonTheme.colors.white)
    }

}