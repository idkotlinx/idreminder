package com.tutorial.learnlinuxpro.presentation.di.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

/**
 * Created by kodeartisan on 12/10/17.
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor
            = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


}