package com.example.android4homework8mc6.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android4homework8mc6.utils.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <T> fetchData(
        id: String?,
        fetchFunction: (String) -> Flow<Either<Throwable, T>>,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        id?.let {
            viewModelScope.launch {
                fetchFunction(id)
                    .catch { throwable ->
                        onError(throwable)
                    }
                    .collect { either ->
                        when (either) {
                            is Either.Left -> {
                                onError(either.message ?: Throwable("Неизвестная ошибка"))
                            }

                            is Either.Right -> {
                                either.data?.let { data -> onSuccess(data) }
                            }
                        }
                    }
            }
        }
    }
}
