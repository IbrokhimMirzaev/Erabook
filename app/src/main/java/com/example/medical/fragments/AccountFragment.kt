package com.example.medical.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import com.example.medical.R
import com.example.medical.databinding.FragmentAccountBinding
import com.example.medical.model.User
import com.google.gson.Gson

class AccountFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAccountBinding.inflate(inflater, container, false)

        val shared = requireContext().getSharedPreferences("shared", AppCompatActivity.MODE_PRIVATE)
        val gson = Gson()
        val userJson = shared.getString("active_user", null)
        val user: User = gson.fromJson(userJson, User::class.java)

        binding.personName.text = user.username
        binding.personEmail.text = user.email

        binding.logOutBtn.setOnClickListener {
            shared.edit().putBoolean("isLoggedOut", true).apply()

            findNavController().popBackStack(R.id.mainFragment, true)
            findNavController().navigate(R.id.welcomeFragment)
        }

        binding.personalInfo.setOnClickListener {
            findNavController().navigate(R.id.personalInfoFragment)
        }

        return binding.root
    }
}