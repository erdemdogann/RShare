package com.example.rshare.presentation.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.rshare.R
import com.example.rshare.data.dto.getdata.ShareGetData
import com.example.rshare.databinding.FragmentHomeBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    val db = Firebase.firestore
    private lateinit var shareList: ArrayList<ShareGetData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        shareList = ArrayList<ShareGetData>()
        getData()

        return binding.root
    }

    private fun getData() {
        db.collection("users").addSnapshotListener { value, error ->
            if (error != null) {
                Toast.makeText(requireContext(), "hata", Toast.LENGTH_SHORT).show()
            } else {
                if (value != null) {
                    if (!value.isEmpty) {
                        val datas = value.documents
                        for (data in datas) {
                            val comment = data.get("comment") as String
                            val users = data.get("user") as String
                            val image = data.get("image") as String

                            println(comment)

                            val post = ShareGetData(users, comment, image)
                            shareList.add(post)
                        }
                    }
                }
            }
        }
    }
}