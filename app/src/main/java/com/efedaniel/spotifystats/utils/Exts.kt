package com.efedaniel.spotifystats.utils

import android.content.Context
import android.util.DisplayMetrics
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import timber.log.Timber

fun Dp.toPx(context: Context): Float {
    return (this.value * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).also {
        Timber.d("Exts converted ${this.value} in dp to $it in px")
    }
}

fun sePaddingModifier(padding: Dp = 16.dp) = Modifier.padding(horizontal = padding)
