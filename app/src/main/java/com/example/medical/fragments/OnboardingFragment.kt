package com.example.medical.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.medical.R
import com.example.medical.databinding.FragmentOnboardingBinding
import pl.droidsonroids.gif.GifDrawable

class OnBoardingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOnboardingBinding.inflate(inflater, container, false)

        val firstText = "Thousands of\ndoctors and experts to help your health!"
        val secondText = "Health checks and\nconsultations easily anywhere anytime"
        val thirdText = "Let's start living healthy and well with us right now!"

        val gifs = mutableListOf(R.drawable.first, R.drawable.second, R.drawable.third)
        val texts = mutableListOf(firstText, secondText, thirdText)

        var index = 1
        var gifDrawable = GifDrawable(resources, gifs[0])
        binding.img.setImageDrawable(gifDrawable)

        binding.next.setOnClickListener {
            if (index <= 2) {
                binding.tv1.text = texts[index]
                gifDrawable = GifDrawable(resources, gifs[index])
                binding.img.setImageDrawable(gifDrawable)
            }

            if (index == 2) {
                binding.next.text = "Get Started"
            }

            if (index == 3) {
                findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment2)
            }

            index++
        }

        return binding.root
    }
}