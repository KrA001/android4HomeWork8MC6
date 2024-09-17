package com.example.android4homework8mc6.ui.fragments.anime

import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android4homework8mc6.R
import com.example.android4homework8mc6.databinding.FragmentAnimeBinding
import com.example.android4homework8mc6.extension.setupPagingProgress
import com.example.android4homework8mc6.ui.adapters.AnimeAdapter
import com.example.android4homework8mc6.ui.fragments.home.HomeFragmentDirections
import com.example.android5kitsuapiteamwork.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AnimeFragment : BaseFragment<FragmentAnimeBinding, AnimeViewModel>(R.layout.fragment_anime) {
    override val binding by viewBinding(FragmentAnimeBinding::bind)
    override val viewModel by viewModels<AnimeViewModel>()
    private val animeAdapter = AnimeAdapter(this::onItemClick)

    private fun onItemClick(id: String) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToAnimeDetailFragment2(
                id
            )
        )
    }

    override fun initialize() {
        binding.rvManga.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = animeAdapter
        }
    }

    override fun setupSubscribes() {
        subscribeToAnime()
        animeLaunch()
    }

    private fun animeLaunch() = with(binding) {
        setupPagingProgress(
            adapter = animeAdapter,
            progressBar = binding.progressBar,
            appendProgress = binding.appendProgress
        )
    }

    private fun subscribeToAnime() {
        viewModel.fetchAnime().observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                Log.e("activity", it.toString())
                animeAdapter.submitData(it)
            }
        }
    }
}
