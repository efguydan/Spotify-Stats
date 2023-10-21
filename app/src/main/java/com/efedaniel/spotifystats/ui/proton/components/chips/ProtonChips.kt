package com.efedaniel.spotifystats.ui.proton.components.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Chip(text: String) {
    // You can customize the appearance of your chips here.
    // This is a simple example with a colored background and padding.
    Box(
        modifier = Modifier.padding(4.dp)
            .clip(shape = CircleShape),
        contentAlignment = Alignment.Center,
        content = {
            Text(
                text = text,
                color = Color.White,
                modifier = Modifier
                    .background(Color.Blue)
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            )
        }
    )
}
