package com.example.rshare.di

import com.example.rshare.data.api.RShareApi
import com.example.rshare.data.api.RShareApi.Companion.ANIME_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAnimeApi(): RShareApi {
        return Retrofit.Builder()
            .baseUrl(ANIME_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RShareApi::class.java)
    }

}