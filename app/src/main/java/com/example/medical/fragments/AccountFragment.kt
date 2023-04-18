package com.example.medical.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.medical.R
import com.example.medical.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAccountBinding.inflate(inflater, container, false)

        val shared = requireContext().getSharedPreferences("shared", AppCompatActivity.MODE_PRIVATE)

        binding.logOutBtn.setOnClickListener {
            findNavController().popBackStack(R.id.mainFragment, true)
            findNavController().navigate(R.id.welcomeFragment)

            shared.edit().putBoolean("isLoggedOut", true).apply()
        }

        return binding.root
    }
}