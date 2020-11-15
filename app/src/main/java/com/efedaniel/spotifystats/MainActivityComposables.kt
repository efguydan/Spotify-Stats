package com.efedaniel.spotifystats

import androidx.compose.foundation.Text
import androidx.compose.material.AmbientEmphasisLevels
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.efedaniel.spotifystats.ui.SpotifyStatsTheme

@Composable
fun MainActivityContent() {
    SpotifyStatsTheme {
        Scaffold(
            bottomBar = {
                BottomNavigation(
                    elevation = 8.dp
                ) {
                    SpotifyStatsNavigationItem(
                        icon = Icons.Filled.Home,
                        label = stringResource(R.string.tracks),
                        onClick = { },
                        isSelected = true
                    )
                    SpotifyStatsNavigationItem(
                        icon = Icons.Filled.Home,
                        label = stringResource(R.string.artists),
                        onClick = { }
                    )
                    SpotifyStatsNavigationItem(
                        icon = Icons.Filled.Home,
                        label = stringResource(R.string.activity),
                        onClick = { }
                    )
                    SpotifyStatsNavigationItem(
                        icon = Icons.Filled.Timeline,
                        label = stringResource(R.string.insights),
                        onClick = { }
                    )
                    SpotifyStatsNavigationItem(
                        icon = Icons.Filled.AccountCircle,
                        label = stringResource(R.string.account),
                        onClick = { }
                    )
                }
            }
        ) {

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
        //TODO change always show labels to false later
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