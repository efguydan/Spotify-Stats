package com.efedaniel.spotifystats.ui.scene.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.efedaniel.spotifystats.ui.scene.music.MusicScreen
import com.efedaniel.spotifystats.ui.scene.overview.OverviewScreen
import com.efedaniel.spotifystats.ui.scene.profile.ProfileScreen
import com.efedaniel.spotifystats.ui.scene.topartist.TopArtistScreen
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

    @OptIn(ExperimentalFoundationApi::class)
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
                    MainDestinations.forEach { destination ->
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
                startDestination = MainDestination.Overview.route,
                modifier = Modifier.padding(padding)
            ) {
                composable(MainDestination.Overview.route) { OverviewScreen() }
                composable(MainDestination.Music.route) { MusicScreen() }
                composable(MainDestination.Artist.route) { TopArtistScreen() }
                composable(MainDestination.Profile.route) { ProfileScreen() }
            }
        }
    }
}
