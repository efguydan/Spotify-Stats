package com.efedaniel.spotifystats.ui.screens.tracks

import androidx.activity.viewModels
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.identityHashCode
import androidx.ui.tooling.preview.Preview
import com.efedaniel.spotifystats.extensions.sePaddingModifier
import com.efedaniel.spotifystats.extensions.toPx
import com.efedaniel.spotifystats.ui.MainActivity
import com.efedaniel.spotifystats.ui.commons.StatsTimeFrame
import com.efedaniel.spotifystats.ui.commons.components.TrackCard
import com.efedaniel.spotifystats.ui.commons.getScrim
import com.efedaniel.spotifystats.ui.commons.layouts.DynamicVerticalGrid
import com.efedaniel.spotifystats.ui.theme.SpotifyStatsTheme
import io.reactivex.rxjava3.core.Observable
import timber.log.Timber

@Composable
fun TracksListContent(
    modifier: Modifier = Modifier,
    timeFrame: StatsTimeFrame
) {
    val viewModel: TracksViewModel by (ContextAmbient.current as MainActivity).viewModels()

    Timber.d(viewModel.identityHashCode().toString())

    // TODO Subscribe to this and use it to render
    // val state = viewModel.states().

    // TODO Create the intents object and pass it to the viewmodel
    viewModel.processIntents(
        Observable.merge(
            Observable.just(TracksIntent.InitialIntent),
            Observable.just(TracksIntent.RefreshIntent(timeFrame))
        )
    )

    ScrollableColumn {
        DynamicVerticalGrid(
            modifier = sePaddingModifier(12.dp).padding(top = 4.dp),
            preferredItemWidth = 120.dp.toPx(ContextAmbient.current).dp
        ) {
            repeat(times = 11) {
                TopTrackCard(modifier = Modifier.padding(all = 4.dp))
            }
        }
    }
}

// TODO Try and fix the way the scrim looks from top to bottom. It is not fading out per se.
// TODO Also Consider using a radial scrim

@Composable
fun TopTrackCard(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        TrackCard()
        Text(
            text = "1",
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .fillMaxWidth()
                .background(brush = getScrim(startY = 0f, endY = 70f))
                .padding(start = 8.dp, top = 4.dp)
        )
    }
}

@Preview
@Composable
fun PreviewTracksListContent() {
    SpotifyStatsTheme { TracksListContent(timeFrame = StatsTimeFrame.WEEKS) }
}
