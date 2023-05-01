package com.example.medical.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.medical.R
import com.example.medical.databinding.FragmentAgeBinding
import com.example.medical.databinding.FragmentSelectGenreBinding
import com.google.android.material.button.MaterialButton

class SelectGenreFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentSelectGenreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectGenreBinding.inflate(inflater, container, false)

        binding.romance.setOnClickListener(this)
        binding.fantasy.setOnClickListener(this)
        binding.scientific.setOnClickListener(this)
        binding.comedy.setOnClickListener(this)
        binding.thriller.setOnClickListener(this)
        binding.adventure.setOnClickListener(this)
        binding.childrens.setOnClickListener(this)
        binding.biography.setOnClickListener(this)
        binding.travel.setOnClickListener(this)

//        binding.continueBtn.setOnClickListener {
//            findNavController().navigate(R.id.action_selectGenreFragment_to_signUpFragment)
//        }
        return binding.root
    }

    private fun changeButtonStyle(button: View) {
        if ((button as MaterialButton).currentTextColor != Color.WHITE) {
            button.setTextColor(Color.WHITE)
            button.setBackgroundColor(Color.parseColor("#FCA82E"))
        }
        else {
            button.setTextColor(Color.parseColor("#FCA82E"))
            button.setBackgroundColor(Color.WHITE)
            button.setStrokeColorResource(R.color.mainColor)
            button.setStrokeWidthResource(R.dimen.stroke_width)
        }
    }

    override fun onClick(p0: View?) {
        changeButtonStyle(p0!!)
    }
}