package com.example.android4homework8mc6.base

import androidx.lifecycle.liveData
import com.example.android4homework8mc6.utils.Resource

open class BaseRepository {

    protected open fun <T> doRequest(request: suspend () -> T) = liveData {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(request()))
        } catch (exception: Exception) {
            emit(Resource.Error(null, exception.localizedMessage ?: "Error"))
        }
    }
}