package com.efedaniel.spotifystats.ui.proton.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.efedaniel.spotifystats.ui.proton.components.text.ProtonText
import com.efedaniel.spotifystats.ui.proton.tokens.dimension.ProtonDimension
import com.efedaniel.spotifystats.utility.extensions.conditional

@Composable
fun ProtonButton(
    text: String,
    type: ProtonButtonType,
    size: ProtonButtonSize,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)? = null,
    fillMaxWidth: Boolean = false,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .conditional(fillMaxWidth) { fillMaxWidth() }
            .requiredHeight(getHeightForSize(size)),
        enabled = enabled,
        shape = getShapeForType(type = type),
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
    ) {
        icon?.let {  lambda ->
            lambda.invoke()
            Spacer(modifier = Modifier.width(ProtonDimension.Spacing4))
        }
        ProtonText(
            text = text,
        )
    }
}