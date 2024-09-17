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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.id.let { id ->
            viewModel.setId(id)
        }
        subscribeToAnime(view)

    }

    override fun setupSubscribes() {

        binding.tvBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun subscribeToAnime(view: View) {
        viewModel.detailState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UiState.Error -> uiState.message?.let {
                    Snackbar.make(
                        requireView(), it, Snackbar.LENGTH_SHORT
                    ).show()
                }

                UiState.Loading -> binding.progressBar.isVisible = true

                is UiState.Success -> {
                    binding.progressBar.isVisible = false
                    uiState.data?.let {
//
                        Glide.with(binding.artView).load(it.attributes.posterImage.original)
                            .into(binding.artView)
                    }
                }
            }
        }
    }
}
