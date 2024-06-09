package com.efedaniel.spotifystats.ui.scene.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.efedaniel.spotifystats.R
import com.efedaniel.spotifystats.ui.proton.components.image.ProtonImage
import com.efedaniel.spotifystats.ui.proton.components.text.ProtonText
import com.efedaniel.spotifystats.ui.proton.tokens.dimension.ProtonDimension
import com.efedaniel.spotifystats.ui.proton.tokens.icon.ProtonIcon
import com.efedaniel.spotifystats.ui.scene.profile.ProfileDestination.LOGIN
import com.efedaniel.spotifystats.ui.scene.profile.ProfileEvent.Logout
import com.efedaniel.spotifystats.utility.extensions.findActivity

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
    navigator: ProfileNavigator = viewModel.navigator
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Header(
            imageUrl = viewModel.state.user.imageUrl.orEmpty(),
            userName = viewModel.state.user.displayName
        )
        Option(
            text = stringResource(R.string.logout),
            onRowClick = { viewModel.onNewEvent(Logout) },
        )
        ProfileDivider()
    }

    viewModel.state.destination?.get()?.let {
        when (it) {
            LOGIN -> navigator.navigateToAuth(findActivity())
        }
    }
}

@Composable
private fun Header(
    imageUrl: String,
    userName: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = ProtonDimension.Spacing16),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ProtonImage(
            url = imageUrl,
            modifier = Modifier
                .requiredSize(ProtonDimension.ImageSize56)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(ProtonDimension.Spacing8))
        ProtonText(text = userName)
    }
}

@Composable
private fun Option(
    text: String,
    onRowClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(ProtonDimension.ComponentSize48)
            .clickable(onClick = onRowClick)
            .padding(horizontal = ProtonDimension.Spacing16),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = text,
            modifier = Modifier.weight(1.0f)
        )
        ProtonIcon(icon = Icons.Filled.KeyboardArrowRight)
    }
}

@Composable
private fun ProfileDivider(modifier: Modifier = Modifier,) {
    Divider(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = ProtonDimension.Spacing16)
    )
}
