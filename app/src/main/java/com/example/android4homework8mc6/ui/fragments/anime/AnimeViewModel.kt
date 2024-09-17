package com.example.android4homework8mc6.ui.fragments.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.android4homework8mc6.data.repositories.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(private val repository: AnimeRepository) : ViewModel(){

    fun fetchAnime() = repository.fetchAnime().cachedIn(viewModelScope)

}