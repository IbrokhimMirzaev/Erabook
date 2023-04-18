package com.example.medical.fragments

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
import com.example.medical.databinding.FragmentGenreBooksBinding
import com.example.medical.model.Book

class GenreBooksFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentGenreBooksBinding.inflate(inflater, container, false)

        var genreName = arguments?.getString("name")
        var allBooks = arguments?.getSerializable("books") as ArrayList<Book>

        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.genreName.text = genreName

        binding.rv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rv.adapter = BookAdapter(allBooks.filter { it.genreName == genreName } as ArrayList<Book>, R.layout.book_item)

        val mainColor = ContextCompat.getColor(requireContext(), R.color.mainColor)
        val blackColor = ContextCompat.getColor(requireContext(), R.color.black)
        binding.myGrid.setColorFilter(mainColor, PorterDuff.Mode.SRC_ATOP)

        binding.myGrid.setOnClickListener {
            binding.rv.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rv.adapter = BookAdapter(allBooks.filter { it.genreName == genreName } as ArrayList<Book>, R.layout.book_item)

            binding.myGrid.setColorFilter(mainColor, PorterDuff.Mode.SRC_ATOP)
            binding.linearGrid.setColorFilter(blackColor, PorterDuff.Mode.SRC_ATOP)
        }

        binding.linearGrid.setOnClickListener {
            binding.rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.rv.adapter = BookAdapter(allBooks.filter { it.genreName == genreName } as ArrayList<Book>, R.layout.book_item2)

            binding.linearGrid.setColorFilter(mainColor, PorterDuff.Mode.SRC_ATOP)
            binding.myGrid.setColorFilter(blackColor, PorterDuff.Mode.SRC_ATOP)
        }

        if ((allBooks.filter { it.genreName == genreName } as ArrayList<Book>).isEmpty()) {
            binding.notFound.visibility = View.VISIBLE
            binding.rv.visibility = View.GONE
        } else {
            binding.rv.visibility = View.VISIBLE
            binding.notFound.visibility = View.GONE
        }


        return binding.root
    }
}