package com.example.medical.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.medical.R
import com.example.medical.databinding.FragmentAgeBinding
import com.google.android.material.button.MaterialButton

class AgeFragment : Fragment(), OnClickListener {
    private lateinit var binding: FragmentAgeBinding
    private var ageButtons = mutableListOf<MaterialButton>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAgeBinding.inflate(inflater, container, false)

        ageButtons = mutableListOf(binding.first, binding.second, binding.third, binding.fourth, binding.fifth, binding.sixth, binding.seventh, binding.eights)

        binding.first.setOnClickListener(this)
        binding.second.setOnClickListener(this)
        binding.third.setOnClickListener(this)
        binding.fourth.setOnClickListener(this)
        binding.fifth.setOnClickListener(this)
        binding.sixth.setOnClickListener(this)
        binding.seventh.setOnClickListener(this)
        binding.eights.setOnClickListener(this)

//        binding.continueBtn.setOnClickListener {
//            findNavController().navigate(R.id.action_ageFragment_to_selectGenreFragment)
//        }
        return binding.root
    }

    fun changeButtonStyle(button: View) {
        for (i in ageButtons) {
            if (i == button as MaterialButton) {
                i.setTextColor(Color.WHITE)
                i.setBackgroundColor(Color.parseColor("#FCA82E"))
            }
            else {
                i.setTextColor(Color.parseColor("#FCA82E"))
                i.setBackgroundColor(Color.WHITE)
                i.setStrokeColorResource(R.color.mainColor)
                i.setStrokeWidthResource(R.dimen.stroke_width)
            }
        }
    }

    override fun onClick(p0: View?) {
        changeButtonStyle(p0!!)
    }
}