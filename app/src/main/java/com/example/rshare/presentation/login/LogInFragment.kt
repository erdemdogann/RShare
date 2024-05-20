package com.example.rshare.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.rshare.R
import com.example.rshare.databinding.FragmentLogInBinding
import com.example.rshare.presentation.signup.SignUpFragmentDirections
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment : Fragment() {

    private lateinit var binding: FragmentLogInBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInBinding.inflate(inflater, container, false)
        auth = Firebase.auth

        binding.signUp.setOnClickListener {
            findNavController().navigate(LogInFragmentDirections.forSignUp())
        }
        logIn()
        return binding.root
    }

    private fun logIn() {
        binding.apply {
            signIn.setOnClickListener {
                val email = email.text.toString()
                val password = password.text.toString()
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                        Toast.makeText(activity, "Giriş Başarılı", Toast.LENGTH_LONG).show()
                    }.addOnFailureListener {
                        Toast.makeText(
                            activity,
                            "Lütfen Bilgilerinizi Kontrol Ediniz",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }
}