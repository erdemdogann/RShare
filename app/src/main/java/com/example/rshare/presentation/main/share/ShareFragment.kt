package com.example.rshare.presentation.main.share

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rshare.R
import com.example.rshare.databinding.FragmentShareBinding
import com.example.rshare.loadImage
import com.example.rshare.loadMovieImage
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ShareFragment : Fragment() {

    private lateinit var binding: FragmentShareBinding
    private val args: ShareFragmentArgs by navArgs()
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentShareBinding.inflate(inflater, container, false)

        setupBinding()

        return binding.root
    }

    private fun setupBinding() {
        binding.apply {
            shareImage.setOnClickListener {
                findNavController().navigate(ShareFragmentDirections.shareAnime())
            }
            val imageName = args.movieId

            val type = args.type
            if (type == "movie") {
                if (imageName?.isNotEmpty() == true) {
                    shareImage.loadMovieImage(imageName)
                }
            }else if (type == "anime"){
                if (imageName?.isNotEmpty() == true) {
                    shareImage.loadImage(imageName)
                }
            }
            share.setOnClickListener {
                if (imageName?.isNotEmpty() == true && comment.text.isNotEmpty()) {
                    val share = hashMapOf(
                        "user" to "Erdem",
                        "image" to "$imageName",
                        "comment" to "${comment.text}",
                        "type" to "$type"
                    )

                    db.collection("users")
                        .add(share)
                        .addOnSuccessListener {
                            Toast.makeText(requireContext(), "Paylaşıldı", Toast.LENGTH_SHORT)
                                .show()
                            findNavController().navigate(ShareFragmentDirections.backMain())
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding document", e)
                        }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Lütfen boş alanları doldurunuz",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}