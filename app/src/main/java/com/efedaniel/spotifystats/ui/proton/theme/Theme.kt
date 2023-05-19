package com.efedaniel.spotifystats.ui.proton.theme

import android.app.Activity
import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.efedaniel.spotifystats.ui.proton.tokens.color.ProtonColor
import com.efedaniel.spotifystats.ui.proton.tokens.color.ProtonColorPalette

private val DarkColorScheme = darkColorScheme(
    primary = ProtonColor.Black,
    onPrimary = ProtonColor.White,
    secondary = ProtonColor.Green,
    onSecondary = ProtonColor.Black,
    tertiary = ProtonColor.White,
    onTertiary = ProtonColor.Black,
    background = ProtonColor.Black,
    onBackground = ProtonColor.White,
    surface = ProtonColor.Black,
    onSurface = ProtonColor.White,
)

private val LightColorScheme = lightColorScheme()

@Composable
fun ProtonTheme(
    darkTheme: Boolean = true,
    lightStatusBarColors: Boolean = true,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Fixme: Do we want this on?
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !lightStatusBarColors
        }
    }

    val colorPalette = ProtonColorPalette()

    CompositionLocalProvider(
        LocalProtonColorPalette provides colorPalette
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

object ProtonTheme {
    val colors: ProtonColorPalette
        @Composable
        get() = LocalProtonColorPalette.current
}

val LocalProtonColorPalette = staticCompositionLocalOf { ProtonColorPalette() }