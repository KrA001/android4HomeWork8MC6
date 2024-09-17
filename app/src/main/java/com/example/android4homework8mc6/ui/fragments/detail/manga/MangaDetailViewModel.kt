package com.example.android4homework8mc6.ui.fragments.detail.manga

import androidx.lifecycle.ViewModel
import com.example.android4homework8mc6.data.repositories.MangaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MangaDetailViewModel @Inject constructor(private val repository: MangaRepository) :
    ViewModel() {

    fun fetchMangaById(id: String) = repository.fetchSingleManga(id)
}