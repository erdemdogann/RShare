package com.example.rshare.data.api

import com.example.rshare.data.dto.anime.AnimeData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeApi {
    companion object {
        const val ANIME_URL = "https://api.jikan.moe"
    }

    @GET("/v4/anime")
    suspend fun getAllAnime(
        @Query("page") page: Int
    ): Response<AnimeData>

}