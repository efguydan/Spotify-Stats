package com.efedaniel.spotifystats.ui.screens.home

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Audiotrack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PeopleAlt
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.ui.graphics.vector.VectorAsset
import com.efedaniel.spotifystats.R
import com.efedaniel.spotifystats.utils.Constants.Destinations

sealed class BottomNavigationScreens(
    val route: String,
    @StringRes val label: Int,
    val icon: VectorAsset
) {
    object Tracks : BottomNavigationScreens(Destinations.TRACKS, R.string.tracks, Icons.Filled.Audiotrack)
    object Artists : BottomNavigationScreens(Destinations.ARTISTS, R.string.artists, Icons.Filled.PeopleAlt)
    object Activity : BottomNavigationScreens(Destinations.ACTIVITY, R.string.activity, Icons.Filled.Home)
    object Insights : BottomNavigationScreens(Destinations.INSIGHTS, R.string.insights, Icons.Filled.Timeline)
    // TODO Decide if this should be account or settings
    object Account : BottomNavigationScreens(Destinations.ACCOUNT, R.string.account, Icons.Filled.AccountCircle)
}
