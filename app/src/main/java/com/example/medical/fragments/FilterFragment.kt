package com.example.medical.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.medical.databinding.FragmentFilterBinding

class FilterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFilterBinding.inflate(inflater, container, false)

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
        }

        binding.applyBtn.setOnClickListener {
            val result = Bundle().apply {
                var checkedRadioButtonId = binding.myRadioGr.checkedRadioButtonId
                var radioButton: RadioButton = binding.root.findViewById(checkedRadioButtonId)
                putString("checkedString", radioButton.text.toString())
            }

            setFragmentResult("myResultKey", result)
            findNavController().popBackStack()
        }

        return binding.root
    }
}