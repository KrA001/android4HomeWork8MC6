package com.example.android4homework8mc6.ui.fragments.anime

import android.os.Bundle
import android.system.Os.bind
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android4homework8mc6.R
import com.example.android4homework8mc6.databinding.FragmentAnimeBinding
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
        binding.animeRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = animeAdapter
        }
    }

    override fun setupSubscribes() {
        subscribeToAnime()
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
