package com.example.android4homework8mc6.base

import androidx.lifecycle.liveData
import com.example.android4homework8mc6.utils.UiState

open class BaseRepository {

    protected open fun <T> doRequest(request: suspend () -> T) = liveData {
        emit(UiState.Loading)
        try {
            emit(UiState.Success(request()))
        } catch (exception: Exception) {
            emit(UiState.Error(exception, exception.localizedMessage ?: "Error"))
        }
    }
}