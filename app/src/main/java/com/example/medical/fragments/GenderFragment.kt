package com.example.medical.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.children
import androidx.navigation.fragment.findNavController
import com.example.medical.R
import com.example.medical.databinding.FragmentGenderBinding

class GenderFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentGenderBinding.inflate(inflater, container, false)
//        binding.continueBtn.setOnClickListener {
//            if (binding.radioGr.checkedRadioButtonId != -1) {
//                findNavController().navigate(R.id.action_genderFragment_to_ageFragment)
//            } else {
//                Toast.makeText(requireContext(), "Please select one of them", Toast.LENGTH_SHORT).show()
//            }
//        }
        return binding.root
    }
}