package com.example.android4homework8mc6.ui.fragments.home

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android4homework8mc6.R
import com.example.android4homework8mc6.databinding.FragmentHomeBinding
import com.example.android4homework8mc6.ui.adapters.AnimeViewPager
import com.example.android5kitsuapiteamwork.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val binding by viewBinding(FragmentHomeBinding::bind)
    override val viewModel: HomeViewModel by viewModels()

    private val fragListNames = listOf(
        "Anime",
        "Manga"
    )

    override fun initialize() {
        val adapter = AnimeViewPager(this)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            tab.text = fragListNames[pos]
        }.attach()
    }
}