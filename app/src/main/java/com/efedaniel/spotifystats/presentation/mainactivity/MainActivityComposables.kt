package com.efedaniel.spotifystats.presentation.mainactivity

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.AmbientEmphasisLevels
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview
import com.efedaniel.spotifystats.navigation.Destinations
import com.efedaniel.spotifystats.ui.SpotifyStatsTheme

val botttomNavDestinations = listOf(
    BottomNavigationScreens.Tracks,
    BottomNavigationScreens.Artists,
    BottomNavigationScreens.Activity,
    BottomNavigationScreens.Insights,
    BottomNavigationScreens.Account
)

@Composable
fun MainActivityContent() {
    SpotifyStatsTheme {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomNavigation(
                    elevation = 8.dp
                ) {
                    val navBackStackEntry = navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry.value?.arguments?.getString(KEY_ROUTE)
                    botttomNavDestinations.forEach {
                        SpotifyStatsNavigationItem(
                            icon = it.icon,
                            label = stringResource(it.label),
                            isSelected = currentRoute == it.route,
                            onClick = {
                                //TODO would most likely do this a different way later
                                navController.popBackStack(navController.graph.startDestination, false)

                                if (currentRoute != it.route) {
                                    navController.navigate(it.route.toUri())
                                }
                            }
                        )
                    }
                }
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = Destinations.tracks
            ) {
                composable(Destinations.tracks) { SampleNavHostDestination(text = "Tracks", color = Color.Yellow) }
                composable(Destinations.artists) { SampleNavHostDestination(text = "Artists", color = Color.Cyan) }
                composable(Destinations.activity) { SampleNavHostDestination(text = "Activity", color = Color.Green) }
                composable(Destinations.insights) { SampleNavHostDestination(text = "Insights", color = Color.Blue) }
                composable(Destinations.account) { SampleNavHostDestination(text = "Account", color = Color.White) }
            }
        }
    }
}

// TODO remove this later
@Composable
fun SampleNavHostDestination(text: String, color: Color) {
    Surface(color = color, modifier = Modifier.fillMaxSize()) {
        Text(text = text, color = Color.Black)
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
        // TODO change always show labels to false later
        alwaysShowLabels = true,
        onClick = onClick,
        label = { Text(label) },
        selectedContentColor = MaterialTheme.colors.secondary,
        unselectedContentColor = AmbientEmphasisLevels.current.medium.applyEmphasis(MaterialTheme.colors.onPrimary),
        modifier = modifier
    )
}

@Preview
@Composable
fun PreviewMainActivityContent() {
    MainActivityContent()
}

@Preview
@Composable
fun PreviewSpotifyStatsNavigationItem() {
    SpotifyStatsNavigationItem(icon = Icons.Filled.Home, label = "Home", onClick = { }, isSelected = true)
}
