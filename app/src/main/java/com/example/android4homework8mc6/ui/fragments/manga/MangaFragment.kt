package com.example.android4homework8mc6.ui.fragments.manga

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
import com.example.android4homework8mc6.databinding.FragmentMangaBinding
import com.example.android4homework8mc6.extension.setupPagingProgress
import com.example.android4homework8mc6.ui.adapters.MangaAdapter
import com.example.android4homework8mc6.ui.fragments.home.HomeFragmentDirections
import com.example.android5kitsuapiteamwork.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MangaFragment : BaseFragment<FragmentMangaBinding, MangaViewModel>(R.layout.fragment_manga) {
    override val binding by viewBinding(FragmentMangaBinding::bind)
    override val viewModel: MangaViewModel by viewModels()
    private val mangaAdapter = MangaAdapter(this::onItemClick)

    private fun onItemClick(id: String) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToMangaDetailFragment2(
                id
            )
        )
    }

    override fun initialize() {
        binding.rvManga.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = mangaAdapter
        }
    }

    override fun setupSubscribes() {
        subscribeToAnime()
        mangaLaunch()
    }

    private fun mangaLaunch() = with(binding) {
        setupPagingProgress(
            adapter = mangaAdapter,
            progressBar = binding.progressBar,
            appendProgress = binding.appendProgress
        )
    }

    private fun subscribeToAnime() {
        viewModel.fetchManga().observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                Log.e("activity", it.toString())
                mangaAdapter.submitData(it)
            }
        }
    }
}