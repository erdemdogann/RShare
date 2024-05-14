package com.example.rshare.data.dto.anime

data class Data(
    val duration: String?,
    val episodes: Int?,
    val images: İmages?,
    val mal_id: Int?,
    val rating: String?,
    val score: Double?,
    val scored_by: Int?,
    val studios: List<Studio>?,
    val synopsis: String?,
    val themes: List<Theme>?,
    val title: String?,
    val title_english: String?,
    val title_japanese: String?,
    val title_synonyms: List<String>?,
    val titles: List<Title>?
)