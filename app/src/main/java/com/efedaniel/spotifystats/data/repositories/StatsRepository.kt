package com.efedaniel.spotifystats.data.repositories

import com.efedaniel.spotifystats.model.general.Track
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class StatsRepository @Inject constructor() {

    fun getTopArtists() {
        TODO("Not Yet Implemented")
    }

    fun getTopTracks(): Single<List<Track>> {
        TODO("Not Yet Implemented")
    }
}
