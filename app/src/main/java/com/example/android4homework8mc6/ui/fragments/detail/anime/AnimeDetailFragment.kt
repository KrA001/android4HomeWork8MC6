package com.example.android4homework8mc6.ui.fragments.detail.anime

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.android4homework8mc6.R
import com.example.android4homework8mc6.databinding.FragmentAnimeDetailBinding
import com.example.android4homework8mc6.utils.Resource
import com.example.android5kitsuapiteamwork.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimeDetailFragment :
    BaseFragment<FragmentAnimeDetailBinding, AnimeDetailViewModel>(R.layout.fragment_anime_detail) {

    override val binding by viewBinding(FragmentAnimeDetailBinding::bind)
    override val viewModel: AnimeDetailViewModel by viewModels()
    private val args by navArgs<AnimeDetailFragmentArgs>()

    override fun setupSubscribes() {
        subscribeToManga()

        binding.tvBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun subscribeToManga() {
        viewModel.fetchIdAnime(args.id).observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show()
                }

                is Resource.Success -> {
                    Glide.with(binding.artView)
                        .load(it.data?.animeModel?.attributes?.posterImage?.original)
                        .into(binding.artView)
                }
            }
        }
    }
}
