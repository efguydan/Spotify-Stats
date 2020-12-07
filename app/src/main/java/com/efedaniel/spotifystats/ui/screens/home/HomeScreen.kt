package com.efedaniel.spotifystats.ui.screens.home

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview
import com.efedaniel.spotifystats.ui.screens.account.AccountScreen
import com.efedaniel.spotifystats.ui.screens.activity.ActivityScreen
import com.efedaniel.spotifystats.ui.screens.artists.ArtistsScreen
import com.efedaniel.spotifystats.ui.screens.tracks.TracksScreen
import com.efedaniel.spotifystats.ui.theme.SpotifyStatsTheme
import timber.log.Timber

val bottomNavDestinations = listOf(
    BottomNavigationScreens.Tracks,
    BottomNavigationScreens.Artists,
    BottomNavigationScreens.Activity,
    BottomNavigationScreens.Insights,
    BottomNavigationScreens.Account
)

@Composable
fun HomeScreen() {
    SpotifyStatsTheme {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomNavigation(
                    elevation = 8.dp
                ) {
                    val navBackStackEntry = navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry.value?.arguments?.getString(KEY_ROUTE)
                    Timber.d("Current Route is $currentRoute")
                    bottomNavDestinations.forEach {
                        SpotifyStatsNavigationItem(
                            icon = it.icon,
                            label = stringResource(it.label),
                            isSelected = currentRoute == it.route,
                            onClick = {
                                if (currentRoute != it.route) {
                                    // TODO would most likely do this a different way later
                                    navController.popBackStack(navController.graph.startDestination, false)

                                    navController.navigate(it.route)
                                }
                            }
                        )
                    }
                }
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = BottomNavigationScreens.Artists.route
            ) {
                composable(BottomNavigationScreens.Tracks.route) { TracksScreen() }
                composable(BottomNavigationScreens.Artists.route) { ArtistsScreen() }
                composable(BottomNavigationScreens.Activity.route) { ActivityScreen() }
                composable(BottomNavigationScreens.Insights.route) { ArtistsScreen() }
                composable(BottomNavigationScreens.Account.route) { AccountScreen() }
            }
        }
    }
}

@Composable
fun SpotifyStatsNavigationItem(
    modifier: Modifier = Modifier,
    icon: VectorAsset,
    label: String,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    BottomNavigationItem(
        icon = { Icon(icon) },
        selected = isSelected,
        alwaysShowLabels = false,
        onClick = onClick,
        label = { Text(label) },
        selectedContentColor = MaterialTheme.colors.secondary,
        unselectedContentColor = MaterialTheme.colors.onPrimary,
        modifier = modifier
    )
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}

@Preview
@Composable
fun PreviewSpotifyStatsNavigationItem() {
    SpotifyStatsNavigationItem(icon = Icons.Filled.Home, label = "Home", onClick = { }, isSelected = true)
}
