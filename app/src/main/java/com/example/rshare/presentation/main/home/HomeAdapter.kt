package com.example.rshare.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rshare.data.dto.getdata.ShareGetData
import com.example.rshare.data.dto.share.ShareData
import com.example.rshare.databinding.SharelistCardBinding
import com.example.rshare.loadImage
import com.example.rshare.loadMovieImage

class HomeAdapter(
    private val shareList: ArrayList<ShareGetData>
) : RecyclerView.Adapter<HomeAdapter.Holder>() {
    inner class Holder(private val binding: SharelistCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(shareData: ShareGetData) {
            binding.apply {
                sharePerson.text = shareData.users
                shareName.text = shareData.comment
                shareImage.loadMovieImage(shareData.image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            SharelistCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return shareList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(shareList[position])
    }
}