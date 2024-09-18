package com.example.android4homework8mc6.utils

import java.io.Serializable

sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Error(val throwable: Serializable, val message: String? = null) : UiState<Nothing>()
    data class Success<out T>(val data: T? = null) : UiState<T>()
}