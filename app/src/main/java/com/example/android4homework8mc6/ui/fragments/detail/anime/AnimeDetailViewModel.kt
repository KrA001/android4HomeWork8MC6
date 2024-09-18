package com.example.android4homework8mc6.ui.fragments.detail.anime

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.android4homework8mc6.base.BaseViewModel
import com.example.android4homework8mc6.data.models.AnimeModel
import com.example.android4homework8mc6.data.repositories.AnimeRepository
import com.example.android4homework8mc6.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimeDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: AnimeRepository
) : BaseViewModel() {

    private val _detailState = MutableLiveData<UiState<AnimeModel>>(UiState.Loading)
    val detailState: LiveData<UiState<AnimeModel>> = _detailState
    private val id = savedStateHandle.get<String>(ID_KEY)

    fun setId(id: String) {
        savedStateHandle[ID_KEY] = id
        fetchAnimeById()
    }

    private fun fetchAnimeById() {
        fetchData(
            id = id,
            fetchFunction = { animeId -> repository.fetchSingleAnime(animeId) },
            onSuccess = { anime ->
                _detailState.value = UiState.Success(anime)
            },
            onError = { throwable ->
                _detailState.value = UiState.Error(throwable, throwable.message ?: "Неизвестная ошибка")
            }
        )
    }

    companion object {
        private const val ID_KEY = "id"
    }
}
