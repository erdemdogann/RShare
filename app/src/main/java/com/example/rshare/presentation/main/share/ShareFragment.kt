package com.example.rshare.presentation.main.share

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rshare.R
import com.example.rshare.databinding.FragmentShareBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ShareFragment : Fragment() {

    private lateinit var binding: FragmentShareBinding
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=FragmentShareBinding.inflate(inflater,container,false)

        setupBinding()

        return binding.root
    }

    private fun setupBinding(){
        binding.apply {
            shareImage.setOnClickListener {
                findNavController().navigate(ShareFragmentDirections.selectMovie())
            }
            val bundle:ShareFragmentArgs by navArgs()
            val imageName= bundle.movieId
            println(imageName)
        }
    }

}