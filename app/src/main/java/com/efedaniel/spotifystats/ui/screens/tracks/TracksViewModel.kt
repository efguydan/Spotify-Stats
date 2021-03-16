package com.efedaniel.spotifystats.ui.screens.tracks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.efedaniel.spotifystats.base.mvi.MviViewModel
import io.reactivex.rxjava3.core.Observable

class TracksViewModel @ViewModelInject constructor(
    private val actionProcessorHolder: TracksActionProcessorHolder
) : ViewModel(), MviViewModel<TracksIntent, TracksViewState> {

    override fun processIntents(intents: Observable<TracksIntent>) {
        TODO("Not yet implemented")
    }

    override fun states(): LiveData<TracksViewState> {
        TODO("Not yet implemented")
    }

}
