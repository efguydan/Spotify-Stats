package com.efedaniel.spotifystats.utils

import com.efedaniel.spotifystats.BuildConfig

object Constants {

    object AppVariables {
        const val DATASTORE_NAME = "global_datastore"
    }

    object Destinations {
        const val TRACKS = "tracks"
        const val ARTISTS = "artists"
        const val ACTIVITY = "activity"
        const val INSIGHTS = "insights"
        const val ACCOUNT = "account"
    }

    object DataStoreKeys

    object ApiKeys {
        const val CLIENT_ID = BuildConfig.CLIENT_ID
        const val CLIENT_SECRET = BuildConfig.CLIENT_SECRET

        const val AUTH_BASE_URL = "https://accounts.spotify.com/"
        const val RESPONSE_TYPE = "code"
        const val REDIRECT_URI = "" // TODO set and whitelist redirect uri
        const val SCOPE = "user-read-recently-played,user-read-playback-state,user-top-read," +
            "user-modify-playback-state,user-follow-modify,user-read-currently-playing," +
            "user-library-modify,user-read-private"
        const val AUTH_FULL_URL = "${AUTH_BASE_URL}authorize?client_id=$CLIENT_ID" +
            "&response_type=$RESPONSE_TYPE&redirect_uri=$REDIRECT_URI&scope=$SCOPE" // TODO Add State
    }

    object Misc {
        const val WEEKND_IMAGE = "https://www.rap-up.com/app/uploads/2020/02/the-weeknd-after-hours.jpg"
        const val SIA_IMAGE = "https://i.scdn.co/image/63e7afb473ac268477b4436dc66510bebbc73791"
    }
}
