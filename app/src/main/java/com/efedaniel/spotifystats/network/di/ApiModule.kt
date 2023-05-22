package com.efedaniel.spotifystats.network.di

import com.efedaniel.spotifystats.network.interceptors.AuthorizationInterceptor
import com.efedaniel.spotifystats.network.interceptors.ErrorInterceptor
import com.efedaniel.spotifystats.utility.constants.Constants.SPOTIFY_AUTH_BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [
    NetworkModule::class
])
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    internal fun provideRetrofit(
        client: OkHttpClient,
        callAdapterFactory: CallAdapter.Factory,
        converterFactory: Converter.Factory,
    ): Retrofit = Retrofit
        .Builder()
        .addCallAdapterFactory(callAdapterFactory)
        .addConverterFactory(converterFactory)
        .client(client)
        .baseUrl(SPOTIFY_AUTH_BASE_URL)
        .build()

    @Provides
    @Singleton
    internal fun provideOkHttpClient(
        authInterceptor: AuthorizationInterceptor,
        errorInterceptor: ErrorInterceptor,
        logger: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(errorInterceptor)
        .addInterceptor(logger)
        .build()

    @Provides
    @Singleton
    internal fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .also { it.level = BODY }

    @Provides
    @Singleton
    internal fun provideConverterFactory(): Converter.Factory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    internal fun provideCallAdapterFactory(): CallAdapter.Factory =
        RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io())

    @Provides
    @Singleton
    internal fun provideGson(): Gson =
        GsonBuilder().create()
}