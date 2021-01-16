package com.efedaniel.spotifystats.ui.screens.tracks

import com.efedaniel.spotifystats.data.repositories.StatsRepository
import com.efedaniel.spotifystats.ui.screens.tracks.TracksAction.LoadTracksAction
import com.efedaniel.spotifystats.ui.screens.tracks.TracksResult.LoadTracksResult
import com.efedaniel.spotifystats.utils.SchedulerProvider
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import javax.inject.Inject

class TracksActionProcessorHolder @Inject constructor(
    private val statsRepository: StatsRepository,
    private val schedulerProvider: SchedulerProvider
) {

    private val loadTracksProcessor =
        ObservableTransformer<LoadTracksAction, LoadTracksResult> { actions ->
            actions.flatMap { action ->
                statsRepository.getTopTracks()
                    .toObservable()
                    .map { LoadTracksResult.Success(it) }
                    .cast(LoadTracksResult::class.java)
                    .onErrorReturn(LoadTracksResult::Failure)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .startWithItem(LoadTracksResult.InFlight)
            }
        }

    internal val actionProcessor =
        ObservableTransformer<TracksAction, TracksResult> { actions ->
            actions.publish { shared ->
                Observable.merge(
                    shared.ofType(LoadTracksAction::class.java).compose(loadTracksProcessor),
                    // TODO Add other actions and remove the repeated one below
                    shared.ofType(LoadTracksAction::class.java).compose(loadTracksProcessor)
                )
            }
        }
}
