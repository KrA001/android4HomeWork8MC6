package com.example.android4homework8mc6.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.android4homework8mc6.base.BaseRepository
import com.example.android4homework8mc6.data.remote.apiservices.MangaApiService
import com.example.android4homework8mc6.data.repositories.pagingsources.MangaPagerSource
import javax.inject.Inject

class MangaRepository @Inject constructor(private val service: MangaApiService) : BaseRepository() {

    fun fetchManga() = Pager(
        PagingConfig(
            pageSize = 10,
            initialLoadSize = 10
        )
    ) {
        MangaPagerSource(service)
    }.liveData

    fun fetchSingleManga(id: String) = doRequest {
        service.fetchSingleManga(id)
    }
}