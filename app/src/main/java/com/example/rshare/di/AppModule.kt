package com.example.rshare.di

import com.example.rshare.data.api.AnimeApi
import com.example.rshare.data.api.AnimeApi.Companion.ANIME_URL
import com.example.rshare.data.api.RShareApi
import com.example.rshare.data.api.RShareApi.Companion.MOVIE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAnimeApi(): AnimeApi {
        return Retrofit.Builder()
            .baseUrl(ANIME_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return OkHttpClient.Builder().addInterceptor(Interceptor {
            val newRequest = it.request().newBuilder()
                .addHeader("accept", "application/json")
                .addHeader(
                    "Authorization",
                    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMjMxNzAwMzk1NDNjNDI3ZjVkNTA1ZjRmNDYzY2Y1OSIsInN1YiI6IjY2MTI5ZTY4ZDc2OGZlMDE3YjQzZDYzMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.n7C4N4iQeUTwZuPRzYKwyCBI8-W10CZii4SUGZEU62c"
                ).build()
            it.proceed(newRequest)
        }).addInterceptor(loggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(okHttpClient: OkHttpClient): RShareApi {
        return Retrofit.Builder()
            .baseUrl(MOVIE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RShareApi::class.java)
    }

}