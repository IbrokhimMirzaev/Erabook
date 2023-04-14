package com.example.medical.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.medical.R
import com.example.medical.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSplashBinding.inflate(inflater, container, false)

        val shared = requireContext().getSharedPreferences("shared", AppCompatActivity.MODE_PRIVATE)
        var users = shared.getString("users", "")

        Handler(Looper.getMainLooper()).postDelayed({
            if (users == "") {
                findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment2)
            }
            else {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment2)
            }
        }, 3000)
        return binding.root
    }
}