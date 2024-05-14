package com.example.rshare.data.dto.anime

data class Pagination(
    val current_page: Int?,
    val has_next_page: Boolean?,
    val items: İtems?,
    val last_visible_page: Int?
)