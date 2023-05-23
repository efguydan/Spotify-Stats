package com.efedaniel.spotifystats.persistence.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
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
        masterKey: MasterKey,
    ): SharedPreferences = EncryptedSharedPreferences.create(
        context,
        PREFS_NAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    @Provides
    @Singleton
    internal fun providesMasterKey(
        @ApplicationContext context: Context,
    ): MasterKey = MasterKey
        .Builder(context, MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private companion object {
        const val MASTER_KEY_ALIAS = "master_key_alias"
        const val PREFS_NAME = "spotify_stats_cache"
    }
}