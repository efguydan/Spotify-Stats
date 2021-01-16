package com.efedaniel.spotifystats.di

import com.efedaniel.spotifystats.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// TODO Make sure this singleton component works

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    fun providesSchedulerProvider(): SchedulerProvider = SchedulerProvider
}
