package com.efedaniel.spotifystats.ui.proton.components.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ChipColors
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.ChipBorder
import androidx.compose.material3.ChipElevation
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.efedaniel.spotifystats.ui.proton.tokens.color.ProtonColor

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Chip(
    modifier : Modifier = Modifier.padding(4.dp),
         text: String,
         textColor : Color = ProtonColor.White,
         onclick : () -> Unit = {},
         shape: Shape = CircleShape,
         enabled : Boolean = false,
         icon: @Composable() (() -> Unit)? = null,
         colors: androidx.compose.material3.ChipColors = SuggestionChipDefaults.suggestionChipColors(),
         elevation: ChipElevation? = SuggestionChipDefaults.suggestionChipElevation(),
         border: ChipBorder? = SuggestionChipDefaults.suggestionChipBorder(),
         interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

) {
    SuggestionChip(modifier = modifier,
        onClick = onclick,
        label = { Text( text = text, color = textColor)},
        shape = shape,
        enabled = enabled,
        icon = icon,
        colors = colors,
        elevation = elevation,
        border = border,
        interactionSource = interactionSource)
}
