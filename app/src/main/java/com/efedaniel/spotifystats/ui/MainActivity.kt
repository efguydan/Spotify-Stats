package com.efedaniel.spotifystats.ui

import android.os.Bundle
import androidx.activity.OnBackPressedDispatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.savedinstancestate.rememberSavedInstanceState
import androidx.compose.ui.platform.setContent
import com.efedaniel.spotifystats.ui.navigation.AmbientBackDispatcher
import com.efedaniel.spotifystats.ui.navigation.Destination
import com.efedaniel.spotifystats.ui.navigation.Navigator
import com.efedaniel.spotifystats.ui.screens.home.HomeScreen
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content(backDispatcher = onBackPressedDispatcher)
        }
    }
}

@Composable
fun Content(backDispatcher: OnBackPressedDispatcher) {
    val navigator: Navigator<Destination> = rememberSavedInstanceState(
        saver = Navigator.saver(backDispatcher)
    ) {
        Navigator(Destination.Home, backDispatcher)
    }
    // val actions = remember(navigator) { Actions(navigator) }

    Providers(AmbientBackDispatcher provides backDispatcher) {
        ProvideWindowInsets {
            Crossfade(navigator.current) { destination ->
                when (destination) {
                    Destination.Home -> HomeScreen()
                }
            }
        }
    }
}
