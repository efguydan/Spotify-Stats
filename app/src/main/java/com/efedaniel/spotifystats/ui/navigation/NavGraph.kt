package com.efedaniel.spotifystats.ui.navigation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class Destination : Parcelable {

    @Parcelize
    object Home : Destination()

    // TODO Add other destinations
}

class Actions(navigator: Navigator<Destination>) {

    val pressOnBack: () -> Unit = {
        navigator.back()
    }

    // TODO Add other actions
}
