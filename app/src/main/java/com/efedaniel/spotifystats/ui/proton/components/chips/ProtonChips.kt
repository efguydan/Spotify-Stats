package com.efedaniel.spotifystats.ui.proton.components.chips

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.SuggestionChip
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.efedaniel.spotifystats.ui.proton.components.text.ProtonText
import com.efedaniel.spotifystats.ui.proton.tokens.color.ProtonColor

@Composable
fun Chip(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = ProtonColor.White,
    onclick: () -> Unit = { },
    shape: Shape = CircleShape,
    enabled: Boolean = false,
) {
    SuggestionChip(
        modifier = modifier.padding(4.dp),
        onClick = onclick,
        label = { ProtonText(text = text, color = textColor) },
        shape = shape,
        enabled = enabled,
    )
}
