package com.example.medical.fragments

import android.content.Context
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.get
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.medical.R
import com.example.medical.databinding.FragmentFilterBinding
import com.google.gson.Gson

class FilterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFilterBinding.inflate(inflater, container, false)

        val shared = requireContext().getSharedPreferences("shared", Context.MODE_PRIVATE)

        var checkedButtonText = shared.getString("radioCheckedText", "")
        var romance = shared.getBoolean("romance", false)
        var thriller = shared.getBoolean("thriller", false)
        var action = shared.getBoolean("action", false)

        if (romance) binding.romance.isChecked = true
        if (thriller) binding.thriller.isChecked = true
        if (action) binding.action.isChecked = true

        if (checkedButtonText != "") {
            for (i in 0 until binding.myRadioGr.childCount) {
                if (binding.myRadioGr.getChildAt(i) is RadioButton) {
                    if ((binding.myRadioGr.getChildAt(i) as RadioButton).text == checkedButtonText) {
                        (binding.myRadioGr.getChildAt(i) as RadioButton).isChecked = true
                        break
                    }
                }
            }
        }

        binding.closeBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.resetBtn.setOnClickListener {
            if (binding.myRadioGr.checkedRadioButtonId != -1) {
                for (i in 0 until binding.myRadioGr.childCount) {
                    if (binding.myRadioGr.getChildAt(i) is RadioButton) {
                        (binding.myRadioGr.getChildAt(i) as RadioButton).isChecked = false
                    }
                }
            }

            shared.edit().putBoolean("isFilter", false).apply()
            shared.edit().putString("radioCheckedText", null).apply()
            shared.edit().putBoolean("romance", false).apply()
            shared.edit().putBoolean("thriller", false).apply()
            shared.edit().putBoolean("action", false).apply()

            Toast.makeText(requireContext(), "Filter reseted!", Toast.LENGTH_SHORT).show()

            findNavController().popBackStack()
        }

        binding.applyBtn.setOnClickListener {
            var checkedRadioButtonId = binding.myRadioGr.checkedRadioButtonId

            if (checkedRadioButtonId != -1) {
                shared.edit().putBoolean("isFilter", true).apply()
                var radioButton: RadioButton = binding.root.findViewById(checkedRadioButtonId)
                shared.edit().putString("radioCheckedText", radioButton.text.toString()).apply()
            }

            if (binding.romance.isChecked) {
                shared.edit().putBoolean("romance", true).apply()
            } else {
                shared.edit().putBoolean("romance", false).apply()
            }
            if (binding.thriller.isChecked) {
                shared.edit().putBoolean("thriller", true).apply()
            } else {
                shared.edit().putBoolean("thriller", false).apply()
            }
            if (binding.action.isChecked) {
                shared.edit().putBoolean("action", true).apply()
            } else {
                shared.edit().putBoolean("action", false).apply()
            }

            findNavController().popBackStack()
        }

        return binding.root
    }
}