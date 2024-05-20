package com.example.rshare.presentation.main.anime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rshare.data.paging.anime.AnimePagingAdapter
import com.example.rshare.databinding.FragmentAnimeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AnimeFragment : Fragment() {

    private lateinit var binding: FragmentAnimeBinding
    private val viewModel by viewModels<AnimeViewModel>()
    private val pagingAdapter = AnimePagingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAnimeBinding.inflate(inflater, container, false)

        setupObserver()
        setupBinding()

        return binding.root
    }

    private fun setupBinding() {
        binding.apply {
            animeRV.adapter = pagingAdapter
            animeRV.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setupObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.anime.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }

}