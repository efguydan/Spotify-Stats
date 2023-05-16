package com.efedaniel.spotifystats.ui.scene.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.efedaniel.spotifystats.ui.proton.components.button.ProtonButton
import com.efedaniel.spotifystats.ui.proton.components.button.ProtonButtonType

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        ProtonButton(
            text = "Login with Spotify",
            type = ProtonButtonType.ROUNDED,
            onClick = { /*TODO*/ },
            fillMaxSize = true,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(all = 16.dp),
        )
    }
}
