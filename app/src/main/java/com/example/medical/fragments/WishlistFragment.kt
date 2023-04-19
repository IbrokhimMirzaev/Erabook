package com.example.medical.fragments

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medical.R
import com.example.medical.adapters.BookAdapter
import com.example.medical.databinding.FragmentWishlistBinding
import com.example.medical.model.Book
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WishlistFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentWishlistBinding.inflate(inflater, container, false)

        val shared = requireContext().getSharedPreferences("shared", Context.MODE_PRIVATE)
        val gson = Gson()
        val booksJson = shared.getString("books", null)
        val books = gson.fromJson<ArrayList<Book>>(booksJson, object : TypeToken<ArrayList<Book>>() {}.type)

        binding.wishlistRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.wishlistRecycler.adapter = BookAdapter(books.filter { it.isWish } as ArrayList<Book>, R.layout.book_item2, object: BookAdapter.MyInterface {
            override fun onItemTap(index: Int) {
                findNavController().navigate(R.id.bookDetailFragment)
            }
        })

        val mainColor = ContextCompat.getColor(requireContext(), R.color.mainColor)
        val blackColor = ContextCompat.getColor(requireContext(), R.color.black)
        binding.myGrid.setColorFilter(mainColor, PorterDuff.Mode.SRC_ATOP)

        binding.myGrid.setOnClickListener {
            binding.wishlistRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.wishlistRecycler.adapter = BookAdapter(books.filter { it.isWish } as ArrayList<Book>, R.layout.book_item, object : BookAdapter.MyInterface {
                override fun onItemTap(index: Int) {
                    findNavController().navigate(R.id.bookDetailFragment)
                }
            })

            binding.myGrid.setColorFilter(mainColor, PorterDuff.Mode.SRC_ATOP)
            binding.linearGrid.setColorFilter(blackColor, PorterDuff.Mode.SRC_ATOP)
        }

        binding.linearGrid.setOnClickListener {
            binding.wishlistRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.wishlistRecycler.adapter = BookAdapter(books.filter { it.isWish } as ArrayList<Book>, R.layout.book_item2, object : BookAdapter.MyInterface {
                override fun onItemTap(index: Int) {
                    findNavController().navigate(R.id.bookDetailFragment)
                }
            })

            binding.linearGrid.setColorFilter(mainColor, PorterDuff.Mode.SRC_ATOP)
            binding.myGrid.setColorFilter(blackColor, PorterDuff.Mode.SRC_ATOP)
        }


        return binding.root
    }
}