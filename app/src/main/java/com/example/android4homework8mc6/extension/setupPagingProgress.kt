package com.example.android4homework8mc6.extension
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun <T : PagingDataAdapter<*, *>> Fragment.setupPagingProgress(
    adapter: T,
    progressBar: View,
    appendProgress: View
) {
    viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            adapter.loadStateFlow.collect { loadState ->
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                appendProgress.isVisible = loadState.source.append is LoadState.Loading
            }
        }
    }
}
