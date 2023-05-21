package com.efedaniel.spotifystats.ui.proton.components.button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.efedaniel.spotifystats.ui.proton.components.text.ProtonText
import com.efedaniel.spotifystats.ui.proton.theme.ProtonTheme
import com.efedaniel.spotifystats.ui.proton.theme.ProtonTheme.colors
import com.efedaniel.spotifystats.ui.proton.tokens.dimension.ProtonDimension
import com.efedaniel.spotifystats.ui.proton.tokens.icon.ProtonIcon
import com.efedaniel.spotifystats.ui.proton.tokens.icon.ProtonIconAsset
import com.efedaniel.spotifystats.utility.extensions.conditional

@Composable
fun ProtonButton(
    text: String,
    type: ProtonButtonType,
    size: ProtonButtonSize,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ProtonIconAsset? = null,
    iconTint: Color = LocalContentColor.current,
    fillMaxWidth: Boolean = false,
    enabled: Boolean = true,
    isLoading: Boolean = false,
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
        enabled = enabled && isLoading.not(),
        shape = getShapeForType(type = type),
        colors = ButtonDefaults.buttonColors(
            containerColor = colors.black,
            contentColor = colors.white,
            disabledContainerColor = colors.black,
            disabledContentColor = colors.white,
        ),
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
    ) {
        icon?.let {
            Box {
                this@Button.AnimatedVisibility(
                    visible = isLoading,
                    enter = fadeIn(),
                    exit = fadeOut(),
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.requiredSize(
                            ProtonDimension.ComponentSize28,
                        ),
                        color = colors.white,
                        strokeWidth = ProtonDimension.Stroke2
                    )
                }

                ProtonIcon(
                    asset = icon,
                    tint = iconTint,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.width(ProtonDimension.Spacing4))
        }
        ProtonText(
            text = text,
        )
    }
}