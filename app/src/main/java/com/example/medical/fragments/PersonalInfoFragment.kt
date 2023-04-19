package com.example.medical.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.medical.R
import com.example.medical.databinding.FragmentPersonalInfoBinding
import com.example.medical.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PersonalInfoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPersonalInfoBinding.inflate(inflater, container, false)

        val shared = requireContext().getSharedPreferences("shared", AppCompatActivity.MODE_PRIVATE)
        val gson = Gson()
        val userJson = shared.getString("active_user", null)
        val user: User = gson.fromJson(userJson, User::class.java)

        println(user.toString())

        binding.userNameInfo.setText(user.username)
        binding.passwordInfo.setText(user.password)
        binding.emailInfo.setText(user.email)

        return binding.root
    }
}