package com.example.android4homework8mc6.ui.fragments.detail.manga

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android4homework8mc6.data.models.AnimeModel
import com.example.android4homework8mc6.data.repositories.MangaRepository
import com.example.android4homework8mc6.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MangaDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: MangaRepository
) :
    ViewModel() {


    private val _detailState = MutableLiveData<UiState<AnimeModel>>(UiState.Loading)
    val detailState: LiveData<UiState<AnimeModel>> = _detailState
    private val id = savedStateHandle.get<String>(ID_KEY)

    fun setId(id: String) {
        savedStateHandle[ID_KEY] = id
    }

    init {
        fetchMangaById()
    }

    private fun fetchMangaById() {
        viewModelScope.launch {
            id?.let {
                repository.fetchSingleManga(id.toInt().toString()).catch {
                    _detailState.value = UiState.Error(it, it.message ?: "Unknown error")
                }
                    .collect {
                        _detailState.value = UiState.Success(it)
                    }
            }
        }
    }


    companion object {
        private const val ID_KEY = "id"
    }

}