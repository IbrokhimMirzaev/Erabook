package com.example.medical.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
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
        binding.genreRecycler.adapter = GenreAdapter(getGenres(), object: GenreAdapter.MyInterface {
            override fun onItemTap(name: String) {
                val bundle = bundleOf("name" to name, "books" to getBooks())
                findNavController().navigate(R.id.genreBooksFragment, bundle)
            }
        })
        binding.genreRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.mainRecycler.adapter = BookAdapter(getBooks())
        binding.mainRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.purchasedRecycler.adapter = BookAdapter(getBooks())
        binding.purchasedRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.wishlistRecycler.adapter = BookAdapter(getBooks())
        binding.wishlistRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        return binding.root
    }
    fun getGenres() : ArrayList<Genre> {
        var genres = ArrayList<Genre>()
        genres.add(Genre("Romance", R.drawable.img_2))
        genres.add(Genre("Thriller", R.drawable.thriller))
        genres.add(Genre("Action", R.drawable.action))
        return genres
    }

    fun getBooks() : ArrayList<Book> {
        var books = ArrayList<Book>()

        books.add(Book(
            "Harry Potter",
            "J.K. Rowling",
            450,
            "\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
            4.7,
            "5.6 mb",
            "$7.50",
            R.drawable.img_1,
            "Action",
            null
        ))

        books.add(Book(
            "Reign of Blood",
            "Quinn Loftis",
            235,
            "\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
            4.9,
            "6.9 mb",
            "$8.50",
            R.drawable.blood,
            "Romance",
            null
        ))

        books.add(Book(
            "Harry Potter",
            "J.K. Rowling",
            235,
            "\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
            4.9,
            "6.9 mb",
            "$8.50",
            R.drawable.harry_potter,
            "Action",
            null
        ))

        books.add(Book(
            "The Vallentine's Hate",
            "Sidney Halston",
            235,
            "\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
            4.7,
            "5.6 mb",
            "$10.50",
            R.drawable.valentines,
            "Romance",
            null
        ))

        books.add(Book(
            "Harry Potter",
            "J.K. Rowling",
            235,
            "\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
            4.9,
            "6.9 mb",
            "$8.50",
            R.drawable.harry_potter2,
            "Action",
            null
        ))

        books.add(Book(
            "Keeper of Secrets",
            "Denise Grover",
            235,
            "\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
            4.7,
            "5.6 mb",
            "$11.50",
            R.drawable.img_3,
            "Romance",
            null
        ))

        return books
    }
}