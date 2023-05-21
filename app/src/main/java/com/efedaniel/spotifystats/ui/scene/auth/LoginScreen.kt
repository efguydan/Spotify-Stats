package com.efedaniel.spotifystats.ui.scene.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.efedaniel.spotifystats.ui.proton.tokens.icon.ProtonIconAsset
import com.efedaniel.spotifystats.ui.scene.auth.state.LoginUiState
import com.efedaniel.spotifystats.utility.extensions.rememberSnackbarHostState
import com.efedaniel.spotifystats.utility.extensions.showSnackbarNow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onConnectWithSpotify: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState = rememberSnackbarHostState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            ) {
                Snackbar(snackbarData = it)
            }
        }
    ) { padding ->
        LoginContent(
            state = viewModel.state,
            onConnectWithSpotify = onConnectWithSpotify,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
        )
    }

    viewModel.state.error?.get()?.let {
        coroutineScope.launch { snackbarHostState.showSnackbarNow(message = it) }
    }
}

@Composable
private fun LoginContent(
    state: LoginUiState,
    onConnectWithSpotify: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
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
            isLoading = state.isConnecting,
            onClick = onConnectWithSpotify,
            fillMaxWidth = true,
            icon = ProtonIconAsset.Spotify,
            iconTint = ProtonTheme.colors.green,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    vertical = ProtonDimension.Spacing64,
                    horizontal = ProtonDimension.Spacing16,
                ),
        )
    }
}
