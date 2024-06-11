package com.example.rshare.data.paging.anime

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rshare.data.dto.anime.Data
import com.example.rshare.databinding.PagingCardBinding
import com.example.rshare.loadImage

class AnimePagingAdapter :
    PagingDataAdapter<Data, AnimePagingAdapter.Holder>(diffCallback) {

    var onClick: (String?) -> Unit = {}

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

    inner class Holder(private val binding: PagingCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(anime: Data?) {
            binding.apply {
                animeName.text =anime?.title
                animeImage.loadImage(anime?.images?.jpg?.image_url)
                root.setOnClickListener {
                    onClick(anime?.images?.jpg?.image_url)
                }
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
