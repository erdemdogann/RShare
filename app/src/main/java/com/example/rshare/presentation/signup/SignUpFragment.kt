package com.example.rshare.presentation.signup

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.rshare.R
import com.example.rshare.databinding.FragmentSignUpBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    //private val binding by viewBinding(FragmentSignUpBinding::bind)
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        // Initialize Firebase Auth
        auth = Firebase.auth
        signUp()

        return binding.root
    }

    private fun signUp() {

        binding.apply {


            signUp.setOnClickListener {
                val email = email.text.toString()
                val password = password.text.toString()
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                        Toast.makeText(activity, "basarılı", Toast.LENGTH_LONG).show()
                    }.addOnFailureListener {
                        Toast.makeText(activity, "başarısız", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

    }
}