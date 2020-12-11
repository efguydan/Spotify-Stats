package com.efedaniel.spotifystats.utils

import com.efedaniel.spotifystats.BuildConfig

object Constants {

    object AppVariables {
        const val datastoreName = "global_datastore"
        const val clientID = BuildConfig.CLIENT_ID
        const val clientSecret = BuildConfig.CLIENT_SECRET
    }

    object Destinations {
        const val tracks = "tracks"
        const val artists = "artists"
        const val activity = "activity"
        const val insights = "insights"
        const val account = "account"
    }

    object Misc {
        const val weekndImage = "https://www.rap-up.com/app/uploads/2020/02/the-weeknd-after-hours.jpg"
        const val siaImage = "https://i.scdn.co/image/63e7afb473ac268477b4436dc66510bebbc73791"
    }
}
