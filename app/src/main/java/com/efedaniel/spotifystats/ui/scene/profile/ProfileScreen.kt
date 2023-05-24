package com.efedaniel.spotifystats.ui.scene.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.efedaniel.spotifystats.ui.proton.components.text.ProtonText

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    ProtonText(
        text = "Hi there, ${viewModel.state.user.displayName}",
        modifier = modifier,
    )
}
