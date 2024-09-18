package com.example.android4homework8mc6.ui.fragments.detail.manga

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android4homework8mc6.data.models.AnimeModel
import com.example.android4homework8mc6.data.repositories.MangaRepository
import com.example.android4homework8mc6.utils.Either
import com.example.android4homework8mc6.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MangaDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: MangaRepository
) : ViewModel() {

    private val _detailState = MutableLiveData<UiState<AnimeModel>>(UiState.Loading)
    val detailState: LiveData<UiState<AnimeModel>> = _detailState
    private val id = savedStateHandle.get<String>(ID_KEY)

    fun setId(id: String) {
        savedStateHandle[ID_KEY] = id
        fetchMangaById()
    }

    private fun fetchMangaById() {
        id?.let {
            viewModelScope.launch {
                repository.fetchSingleManga(it)
                    .catch { throwable ->
                        _detailState.value = UiState.Error(throwable, throwable.message ?: "Unknown error")
                    }
                    .collect { either ->
                        _detailState.value = when (either) {
                            is Either.Left -> UiState.Error(either.message ?: "Error", either.message?.toString() ?: "Unknown error")
                            is Either.Right -> UiState.Success(either.data)
                        }
                    }
            }
        }
    }

    companion object {
        private const val ID_KEY = "id"
    }
}
