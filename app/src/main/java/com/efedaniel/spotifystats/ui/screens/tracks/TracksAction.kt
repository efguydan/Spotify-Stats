package com.efedaniel.spotifystats.ui.screens.tracks

import com.efedaniel.spotifystats.base.mvi.MviAction
import com.efedaniel.spotifystats.model.general.Track
import com.efedaniel.spotifystats.ui.commons.StatsTimeFrame

sealed class TracksAction : MviAction {

    data class LoadTracksAction(val timeFrame: StatsTimeFrame) : TracksAction()

    data class SelectTrackAction(val track: Track) : TracksAction()

    // TODO Add other possible actions here
}
