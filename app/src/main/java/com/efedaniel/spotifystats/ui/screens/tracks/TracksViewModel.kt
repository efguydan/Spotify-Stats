package com.efedaniel.spotifystats.ui.screens.tracks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.efedaniel.spotifystats.base.mvi.MviViewModel
import com.efedaniel.spotifystats.extensions.notOfType
import com.efedaniel.spotifystats.ui.screens.tracks.TracksResult.LoadTracksResult
import com.efedaniel.spotifystats.ui.screens.tracks.TracksResult.SelectTracksResult
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.subjects.PublishSubject

class TracksViewModel @ViewModelInject constructor(
    private val actionProcessorHolder: TracksActionProcessorHolder
) : ViewModel(), MviViewModel<TracksIntent, TracksViewState> {

    private val intentsSubject: PublishSubject<TracksIntent> = PublishSubject.create()
    private val statesObservable: Observable<TracksViewState> = compose()
    private val disposables = CompositeDisposable()

    private fun compose(): Observable<TracksViewState> {
        return intentsSubject
            .compose<TracksIntent>(intentsFilter)
            .map<TracksAction>(this::actionFromIntent)
            .compose(actionProcessorHolder.actionProcessor)
            .scan(TracksViewState.idle(), reducer)
            .distinctUntilChanged()
            .replay(1)
            .autoConnect(0)
    }

    private fun actionFromIntent(intent: TracksIntent): TracksAction {
        TODO("Not yet implemented")
    }

    private val intentsFilter: ObservableTransformer<TracksIntent, TracksIntent>
        get() = ObservableTransformer { intents ->
            intents.publish { shared ->
                Observable.merge(
                    shared.ofType(TracksIntent.InitialIntent::class.java).take(1),
                    shared.notOfType(TracksIntent.InitialIntent::class.java)
                )
            }
        }

    override fun processIntents(intents: Observable<TracksIntent>) {
        disposables.add(intents.subscribe(intentsSubject::onNext))
    }

    override fun states(): Observable<TracksViewState> = statesObservable

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    companion object {
        private val reducer = BiFunction { previousState: TracksViewState, result: TracksResult ->
            when(result) {
                is LoadTracksResult -> TODO("Not Yet Implemented")
                is SelectTracksResult -> TODO("Not Yet Implemented")
            }
        }
    }

}
