package com.example.android4homework8mc6.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.android4homework8mc6.base.BaseRepository
import com.example.android4homework8mc6.data.models.AnimeModel
import com.example.android4homework8mc6.data.remote.apiservices.MangaApiService
import com.example.android4homework8mc6.data.repositories.pagingsources.MangaPagerSource
import com.example.android4homework8mc6.utils.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    fun fetchSingleManga(id: String): Flow<Either<Throwable, AnimeModel>> = flow {
        try {
            val manga = service.fetchSingleManga(id).animeModel
            emit(Either.Right(manga))
        } catch (e: Throwable) {
            emit(Either.Left(e))
        }
    }

}