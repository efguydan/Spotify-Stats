package com.efedaniel.spotifystats.ui.proton.tokens.icon

import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.efedaniel.spotifystats.ui.proton.tokens.dimension.ProtonDimension

@Composable
fun ProtonIcon(
    asset: ProtonIconAsset,
    modifier: Modifier = Modifier,
    size: Dp = ProtonDimension.IconSize24,
    tint: Color = LocalContentColor.current,
) {
    Icon(
        painter = painterResource(id = asset.iconRes),
        contentDescription = asset.contentDescription,
        tint = tint,
        modifier = modifier.requiredSize(size),
    )
}