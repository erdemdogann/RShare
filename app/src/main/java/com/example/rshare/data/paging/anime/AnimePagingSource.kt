package com.example.rshare.data.paging.anime

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rshare.data.api.AnimeApi
import com.example.rshare.data.dto.anime.Data
import retrofit2.HttpException

private const val MAX_PAGE = 500
class AnimePagingSource(
    private val api:AnimeApi
):PagingSource<Int, Data>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val currentPage = params.key ?: 1

            val response = api.getAllAnime(currentPage)
            val data = if (response.isSuccessful) response.body()?.data.orEmpty()
            else throw HttpException(response)

            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = if (currentPage != MAX_PAGE) currentPage.plus(1) else null,
            )

        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}