package com.example.android4homework8mc6.base

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

abstract class BasePagingSource<Value : Any> : PagingSource<Int, Value>() {

    abstract suspend fun fetchData(params: LoadParams<Int>): LoadResult<Int, Value>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> {
        return try {
            fetchData(params)
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Value>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}