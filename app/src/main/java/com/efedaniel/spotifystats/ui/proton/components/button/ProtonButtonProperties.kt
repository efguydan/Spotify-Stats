package com.efedaniel.spotifystats.ui.proton.components.button

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.efedaniel.spotifystats.ui.proton.tokens.dimension.ProtonDimension

@Composable
fun getShapeForType(type: ProtonButtonType): Shape = when (type) {
    ProtonButtonType.PRIMARY -> RoundedCornerShape(2.dp) // FixMe: Define Dimensions
    ProtonButtonType.ROUNDED -> RoundedCornerShape(percent = 50) // Fixme: Define percentages
}

@Composable
fun getHeightForSize(size: ProtonButtonSize) = when(size) {
    ProtonButtonSize.SMALL -> ProtonDimension.ComponentSize24
    ProtonButtonSize.MEDIUM -> ProtonDimension.ComponentSize36
    ProtonButtonSize.LARGE -> ProtonDimension.ComponentSize48
}