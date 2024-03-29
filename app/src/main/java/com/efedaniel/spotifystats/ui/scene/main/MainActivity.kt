package com.efedaniel.spotifystats.ui.scene.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.efedaniel.spotifystats.ui.proton.components.text.ProtonText
import com.efedaniel.spotifystats.ui.proton.theme.ProtonTheme
import com.efedaniel.spotifystats.ui.proton.tokens.icon.ProtonIcon
import com.efedaniel.spotifystats.ui.scene.artist.ArtistScreen
import com.efedaniel.spotifystats.ui.scene.main.MainDestination.Artist
import com.efedaniel.spotifystats.ui.scene.overview.OverviewScreen
import com.efedaniel.spotifystats.ui.scene.profile.ProfileScreen
import com.efedaniel.spotifystats.ui.scene.topartist.TopArtistScreen
import com.efedaniel.spotifystats.ui.scene.toptrack.TopTrackScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProtonTheme {
                MainScreen()
            }
        }
    }

    @Composable
    fun MainScreen() {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        Scaffold(
            bottomBar = {
                BottomNavigation(
                    backgroundColor = ProtonTheme.colors.black,
                ) {
                    BottomDestinations.forEach { destination ->
                        BottomNavigationItem(
                            icon = { ProtonIcon(asset = destination.icon) },
                            label = { ProtonText(stringResource(destination.titleRes)) },
                            selected = currentDestination?.route == destination.route,
                            alwaysShowLabel = false,
                            onClick = {
                                navController.navigate(destination.route) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                        )
                    }
                }
            }
        ) { padding ->
            NavHost(
                navController = navController,
                startDestination = MainBottomDestination.Overview.route,
                modifier = Modifier.padding(padding)
            ) {
                composable(MainBottomDestination.Overview.route) { OverviewScreen() }
                composable(MainBottomDestination.Profile.route) { ProfileScreen() }
                composable(MainBottomDestination.TopTrack.route) { TopTrackScreen() }
                composable(MainBottomDestination.TopArtist.route) { TopArtistScreen(navController) }

                composable(Artist.route, Artist.arguments) {
                    ArtistScreen(it.arguments?.getString("id"))
                }
            }
        }
    }
}
