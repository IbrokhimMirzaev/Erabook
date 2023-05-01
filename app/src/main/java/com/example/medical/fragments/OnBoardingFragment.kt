package com.example.medical.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.medical.adapters.Pager2Adapter
import com.example.medical.databinding.FragmentOnBoardingBinding
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardingFragment : Fragment() {
    private var fragmentList = mutableListOf<Fragment>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOnBoardingBinding.inflate(inflater, container, false)

        addToList()

        binding.vp.adapter = Pager2Adapter(requireActivity(), fragmentList)
        TabLayoutMediator(binding.tabLayout, binding.vp) { tab, position -> }.attach()

        return binding.root
    }

    private fun addToList() {
        fragmentList.add(GenderFragment())
        fragmentList.add(AgeFragment())
        fragmentList.add(SelectGenreFragment())
        fragmentList.add(SignUpFragment())
    }
}