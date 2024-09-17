package com.example.android4homework8mc6.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.android4homework8mc6.base.BaseRepository
import com.example.android4homework8mc6.data.remote.apiservices.AnimeApiService
import com.example.android4homework8mc6.data.repositories.pagingsources.AnimePagingSource
import javax.inject.Inject

class   AnimeRepository @Inject constructor(private val service: AnimeApiService) : BaseRepository() {

    fun fetchAnime() = Pager(
        PagingConfig(
            pageSize = 10,
            initialLoadSize = 10
        )
    ) {
        AnimePagingSource(service)
    }.liveData

    fun fetchSingleAnime(id: String) = doRequest {
        service.fetchSingleAnime(id)
    }
}