package com.example.medical.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medical.R
import com.example.medical.adapters.BookAdapter
import com.example.medical.adapters.GenreAdapter
import com.example.medical.databinding.FragmentHomeBinding
import com.example.medical.model.Book
import com.example.medical.model.Genre

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.genreRecycler.adapter = GenreAdapter(getGenres())
        binding.genreRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.mainRecycler.adapter = BookAdapter(getBooks())
        binding.mainRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.purchasedRecycler.adapter = BookAdapter(getBooks())
        binding.purchasedRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return binding.root
    }
    fun getGenres() : ArrayList<Genre> {
        var genres = ArrayList<Genre>()
        for (i in 0..10) {
            genres.add(Genre("Romance", R.drawable.img_2))
        }
        return genres
    }

    fun getBooks() : ArrayList<Book> {
        var books = ArrayList<Book>()
        for (i in 0..10) {
            books.add(Book(
                "Harry Potter",
                "J.K. Rowling",
                450,
                "\n" +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
                4.7,
                "5.6 mb",
                "$7.50",
                R.drawable.img_1,
                null,
                null
            ))
        }
        return books
    }
}