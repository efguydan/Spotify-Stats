package com.efedaniel.spotifystats.utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

object SchedulerProvider {

    fun computation(): Scheduler = Schedulers.computation()

    fun io(): Scheduler = Schedulers.io()

    fun ui(): Scheduler = AndroidSchedulers.mainThread()
}
