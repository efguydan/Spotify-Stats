package com.efedaniel.spotifystats.ui.screens.tracks

import com.efedaniel.spotifystats.base.mvi.MviIntent
import com.efedaniel.spotifystats.model.general.Track
import com.efedaniel.spotifystats.ui.commons.StatsTimeFrame

sealed class TracksIntent : MviIntent {

    object InitialIntent : TracksIntent()

    data class RefreshIntent(val timeFrame: StatsTimeFrame) : TracksIntent()

    data class SelectTrackIntent(val track: Track) : TracksIntent()

    // TODO Add other possible intents here
}
