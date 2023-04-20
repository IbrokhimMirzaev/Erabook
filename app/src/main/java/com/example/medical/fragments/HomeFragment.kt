package com.example.medical.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medical.R
import com.example.medical.adapters.BookAdapter
import com.example.medical.adapters.GenreAdapter
import com.example.medical.books.BookApi
import com.example.medical.databinding.FragmentHomeBinding
import com.example.medical.model.Book
import com.example.medical.model.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)


        val shared = requireContext().getSharedPreferences("shared", Context.MODE_PRIVATE)
        val gson = Gson()

        if (shared.getString("books", null) == null) {
            BookApi(requireContext()).saveAllBooksToShared()
        }

        var booksJson = shared.getString("books", null)
        var books = gson.fromJson<ArrayList<Book>>(booksJson, object : TypeToken<ArrayList<Book>>() {}.type)

        binding.genreRecycler.adapter = GenreAdapter(getGenres(), object: GenreAdapter.MyInterface {
            override fun onItemTap(index: Int) {
                val bundle = bundleOf("index" to index)
                findNavController().navigate(R.id.genreBooksFragment, bundle)
            }
        })
        binding.genreRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.mainRecycler.adapter = BookAdapter(books, R.layout.book_item, object : BookAdapter.MyInterface {
            override fun onItemTap(index: Int) {
                var bundle = bundleOf("index" to index)
                findNavController().navigate(R.id.bookDetailFragment, bundle)
            }
        })
        binding.mainRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        if ((books.filter { it.isSaved } as ArrayList<Book>).isEmpty()) {
            binding.box1.visibility = View.VISIBLE
            binding.purchasedRecycler.visibility = View.GONE
        }
        else {
            binding.box1.visibility = View.GONE
            binding.purchasedRecycler.visibility = View.VISIBLE
        }

        if ((books.filter { it.isWish } as ArrayList<Book>).isEmpty()) {
            binding.box2.visibility = View.VISIBLE
            binding.wishlistRecycler.visibility = View.GONE
        }
        else {
            binding.box2.visibility = View.GONE
            binding.wishlistRecycler.visibility = View.VISIBLE
        }

        binding.purchasedRecycler.adapter = BookAdapter(books.filter { it.isSaved } as ArrayList<Book>, R.layout.book_item)
        binding.purchasedRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.wishlistRecycler.adapter = BookAdapter(books.filter { it.isWish } as ArrayList<Book>, R.layout.book_item)
        binding.wishlistRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.editText.addTextChangedListener {
            if (binding.editText.text.toString().isNotEmpty()) {
                binding.others.visibility = View.GONE
                var filterBooks: ArrayList<Book> = ArrayList()
                for (i in books) {
                    if (i.name.lowercase().trim().contains(binding.editText.text.toString().lowercase().trim())) {
                        filterBooks.add(i)
                    }
                }
                binding.mainRecycler.adapter = BookAdapter(filterBooks)
            } else {
                binding.mainRecycler.adapter = BookAdapter(books)
                binding.others.visibility = View.VISIBLE
            }
        }

        binding.editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.editText.clearFocus()
            }
            false
        }

        return binding.root
    }
    fun getGenres() : ArrayList<Genre> {
        var genres = ArrayList<Genre>()
        genres.add(Genre("Romance", R.drawable.img_2))
        genres.add(Genre("Thriller", R.drawable.thriller))
        genres.add(Genre("Action", R.drawable.action))
        return genres
    }
}