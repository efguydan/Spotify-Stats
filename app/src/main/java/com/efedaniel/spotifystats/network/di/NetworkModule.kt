package com.efedaniel.spotifystats.network.di

import com.efedaniel.spotifystats.network.service.AuthApi
import com.efedaniel.spotifystats.network.service.PlaybackService
import com.efedaniel.spotifystats.network.service.StatsApi
import com.efedaniel.spotifystats.network.service.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideAuthApi(
        @Named("Auth_Retrofit") retrofit: Retrofit
    ): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    internal fun provideUserApi(retrofit: Retrofit): UserApi =
        retrofit.create(UserApi::class.java)

    @Provides
    @Singleton
    internal fun provideStatsApi(retrofit: Retrofit): StatsApi =
        retrofit.create(StatsApi::class.java)

    @Provides
    @Singleton
    internal fun providePlaybackService(retrofit: Retrofit): PlaybackService =
        retrofit.create(PlaybackService::class.java)
}