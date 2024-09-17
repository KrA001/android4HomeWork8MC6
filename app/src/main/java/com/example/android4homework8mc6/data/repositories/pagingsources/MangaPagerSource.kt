package com.example.android4homework8mc6.data.repositories.pagingsources

import android.net.Uri
import com.example.android4homework8mc6.base.BasePagingSource
import com.example.android4homework8mc6.data.models.MangaModel
import com.example.android4homework8mc6.data.remote.apiservices.MangaApiService

private const val CHARACTER_STARTING_PAGE_INDEX = 1

class MangaPagerSource(private var service: MangaApiService) : BasePagingSource<MangaModel>() {

    override suspend fun fetchData(params: LoadParams<Int>): LoadResult<Int, MangaModel> {
        val position = params.key ?: CHARACTER_STARTING_PAGE_INDEX
        val pageSize = params.loadSize
        val response = service.fetchManga(pageSize, position)
        val next = response.links.next
        val nextPageNumber = Uri.parse(next).getQueryParameter("page[offset]")!!.toInt()
        return LoadResult.Page(
            data = response.data,
            prevKey = null,
            nextKey = nextPageNumber
        )
    }
}