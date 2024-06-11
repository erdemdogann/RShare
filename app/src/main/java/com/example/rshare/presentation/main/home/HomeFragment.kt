package com.example.rshare.presentation.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rshare.R
import com.example.rshare.data.dto.getdata.ShareGetData
import com.example.rshare.databinding.FragmentHomeBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    val db = Firebase.firestore
    private lateinit var shareList: ArrayList<ShareGetData>
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        shareList = ArrayList<ShareGetData>()
        adapter = HomeAdapter(shareList)
        getData()
        uiSet()

        return binding.root
    }

    private fun uiSet() {
        binding.apply {
            shareList.adapter = adapter
            shareList.layoutManager = LinearLayoutManager(context)
            share.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.gotoShare())
            }
        }
    }

    private fun getData() {
        db.collection("users").get().addOnSuccessListener { result ->
            if (result != null) {
                val datas = result.documents
                for (data in datas) {
                    val comment = data.get("comment") as String
                    val users = data.get("user") as String
                    val image = data.get("image") as String
                    val type = data.get("type") as String

                    println(comment)

                    val post = ShareGetData(users, comment, image, type)
                    shareList.add(post)
                }
                adapter.notifyDataSetChanged()
            }
        }
    }
}