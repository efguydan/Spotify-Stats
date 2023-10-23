package com.efedaniel.spotifystats.ui.proton.components.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.SuggestionChip
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.efedaniel.spotifystats.ui.proton.tokens.color.ProtonColor

@Composable
fun Chip(text: String,
         textColor : Color = ProtonColor.White,
         onclick : () -> Unit = {},
         shape: Shape = CircleShape,
         modifier : Modifier = Modifier.padding(4.dp)
) {
    SuggestionChip(modifier = modifier,
        onClick = onclick,
        label = { Text( text = text, color = textColor)},
        shape = shape)
}
