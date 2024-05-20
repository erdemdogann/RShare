package com.example.rshare.presentation.main.allmovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapi.data.paging.allmovie.AllMoviePagingAdapter
import com.example.rshare.databinding.FragmentAllMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllMovieFragment : Fragment() {
    private lateinit var binding: FragmentAllMovieBinding
    private val viewModel by viewModels<AllMovieViewModel>()
    private val pagingAdapter = AllMoviePagingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllMovieBinding.inflate(inflater, container, false)

        setupObserver()
        setupBinding()

        return binding.root
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewModel.movies.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupBinding() {
        binding.apply {

            allMovie.apply {
                adapter = pagingAdapter
                layoutManager = GridLayoutManager(requireContext(), 2)
            }
        }
    }
}