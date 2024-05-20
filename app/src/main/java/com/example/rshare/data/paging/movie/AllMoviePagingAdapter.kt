package com.example.movieapi.data.paging.allmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapi.data.dto.movie.Result
import com.example.rshare.databinding.PagingCardBinding
import com.example.rshare.loadMovieImage

class AllMoviePagingAdapter :
    PagingDataAdapter<Result, AllMoviePagingAdapter.Holder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }

    class Holder(private val binding: PagingCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Result?) {
            binding.apply {
                animeName.text = movie?.title
                animeImage.loadMovieImage(movie?.poster_path)
            }
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(PagingCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

}