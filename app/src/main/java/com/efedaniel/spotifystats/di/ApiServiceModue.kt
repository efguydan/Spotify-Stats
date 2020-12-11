package com.efedaniel.spotifystats.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// TODO Make sure this singleton component works

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule
