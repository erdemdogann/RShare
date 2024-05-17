package com.example.rshare.presentation.main.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rshare.data.api.RShareApi
import com.example.rshare.data.paging.anime.AnimePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val api: RShareApi
) : ViewModel() {
    val movies = Pager(
    PagingConfig(pageSize = 20)
    ) {
        AnimePagingSource(api)
    }.flow
    .cachedIn(viewModelScope)
}