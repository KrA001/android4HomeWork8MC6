package com.example.android4homework8mc6.ui.fragments.detail.anime

import androidx.lifecycle.ViewModel
import com.example.android4homework8mc6.data.repositories.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimeDetailViewModel @Inject constructor(private val repository: AnimeRepository) :
    ViewModel() {

    fun fetchIdAnime(id: String) = repository.fetchSingleAnime(id)
}