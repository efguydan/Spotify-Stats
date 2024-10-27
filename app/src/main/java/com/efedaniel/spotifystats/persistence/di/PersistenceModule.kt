package com.efedaniel.spotifystats.persistence.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module()
@InstallIn(SingletonComponent::class)
class PersistenceModule {

    @Provides
    @Singleton
    internal fun providesSharedPrefs(
        @ApplicationContext context: Context,
    ): SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private companion object {
        const val PREFS_NAME = "spotify_stats_cache"
    }
}