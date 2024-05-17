package com.example.rshare.data.paging.anime

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rshare.data.dto.anime.Data
import com.example.rshare.databinding.PagingCardBinding

class AnimePagingAdapter :
    PagingDataAdapter<Data, AnimePagingAdapter.Holder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.mal_id == newItem.mal_id
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }
        }
    }

    class Holder(private val binding: PagingCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(anime: Data?) {
            binding.apply {
                // Bind data to views here, for example:
                // textViewTitle.text = anime?.title
                // imageViewCover.setImageURI(anime?.imageUrl)
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
