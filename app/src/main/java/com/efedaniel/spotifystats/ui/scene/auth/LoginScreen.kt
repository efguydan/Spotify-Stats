package com.efedaniel.spotifystats.ui.scene.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.efedaniel.spotifystats.R
import com.efedaniel.spotifystats.ui.proton.components.button.ProtonButton
import com.efedaniel.spotifystats.ui.proton.components.button.ProtonButtonSize
import com.efedaniel.spotifystats.ui.proton.components.button.ProtonButtonType
import com.efedaniel.spotifystats.ui.proton.theme.ProtonTheme
import com.efedaniel.spotifystats.ui.proton.tokens.dimension.ProtonDimension
import com.efedaniel.spotifystats.ui.proton.tokens.icon.ProtonIcon
import com.efedaniel.spotifystats.ui.proton.tokens.icon.ProtonIconAsset

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.pexels_cottonbro_studio_6877351),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        ProtonButton(
            text = stringResource(R.string.connect_with_spotify),
            size = ProtonButtonSize.LARGE,
            type = ProtonButtonType.ROUNDED,
            onClick = viewModel::onConnectSpotifyClicked,
            fillMaxWidth = true,
            icon = {
                ProtonIcon(
                    asset = ProtonIconAsset.Spotify,
                    tint = ProtonTheme.colors.green
                )
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    vertical = ProtonDimension.Spacing48,
                    horizontal = ProtonDimension.Spacing16,
                ),
        )
    }
}
