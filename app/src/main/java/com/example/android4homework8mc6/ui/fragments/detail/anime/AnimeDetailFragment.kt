package com.example.android4homework8mc6.ui.fragments.detail.anime

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.android4homework8mc6.R
import com.example.android4homework8mc6.databinding.FragmentAnimeDetailBinding
import com.example.android4homework8mc6.utils.UiState
import com.example.android5kitsuapiteamwork.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimeDetailFragment :
    BaseFragment<FragmentAnimeDetailBinding, AnimeDetailViewModel>(R.layout.fragment_anime_detail) {

    override val binding by viewBinding(FragmentAnimeDetailBinding::bind)
    override val viewModel: AnimeDetailViewModel by viewModels()
    private val args by navArgs<AnimeDetailFragmentArgs>()

    override fun initialize() {
        args.id.let { id -> viewModel.setId(id) }
    }

    override fun setupListeners() {
        binding.tvBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun setupSubscribes() {
        viewModel.detailState.observe(viewLifecycleOwner) { uiState ->
            binding.progressBar.isVisible = uiState is UiState.Loading

            when (uiState) {
                is UiState.Error -> uiState.message?.let { showSnackbar(it) }
                is UiState.Success -> uiState.data?.let { anime ->
                    loadImage(anime.attributes.posterImage.original)
                }
                else -> Unit
            }
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    private fun loadImage(url: String) {
        Glide.with(binding.artView)
            .load(url)
            .into(binding.artView)
    }
}
