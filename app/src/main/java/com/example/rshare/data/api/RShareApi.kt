package com.example.rshare.data.api

import com.example.movieapi.data.dto.movie.MovieData
import com.example.rshare.data.dto.anime.AnimeData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RShareApi {
    companion object {
        const val ANIME_URL = "https://api.jikan.moe/v4/"
        const val MOVIE_URL = "https://api.themoviedb.org"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
    }

    @GET("/anime")
    suspend fun getAllAnime(
        @Query("page") page: Int
    ): Response<AnimeData>

    @GET("/3/discover/movie")
    suspend fun getAllMovie(
        @Query("page") page: Int,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "tr-TR",
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): Response<MovieData>
}