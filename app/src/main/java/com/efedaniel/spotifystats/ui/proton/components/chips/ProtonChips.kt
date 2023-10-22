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
import androidx.compose.ui.unit.dp
import com.efedaniel.spotifystats.ui.proton.tokens.color.ProtonColor

@Composable
fun Chip(genre: String) {

    SuggestionChip(modifier = Modifier.padding((4.dp)),
        onClick = { /*TODO*/ },
        label = { Text( text = genre, color = ProtonColor.White)})
}
