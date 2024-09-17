package com.example.android4homework8mc6.utils

sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Error(val throwable: Throwable, val message: String? = null) : UiState<Nothing>()
    data class Success<out T>(val data: T? = null) : UiState<T>()
}