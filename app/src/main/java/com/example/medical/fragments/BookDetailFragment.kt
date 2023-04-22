package com.example.medical.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.medical.R
import com.example.medical.databinding.FragmentBookDetailBinding
import com.example.medical.model.Book
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BookDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentBookDetailBinding.inflate(inflater, container, false)

        val shared = requireContext().getSharedPreferences("shared", Context.MODE_PRIVATE)
        val gson = Gson()
        val booksJson = shared.getString("books", null)
        val books = gson.fromJson<ArrayList<Book>>(booksJson, object : TypeToken<ArrayList<Book>>() {}.type)

        var book = arguments?.getSerializable("book") as Book

        var updatableBooks = books

        if (book.isSaved) {
            binding.saved.setImageResource(R.drawable.saved_selected)
        }
        else {
            binding.saved.setImageResource(R.drawable.saved)
        }

        if (book.isWish) {
            binding.wishlist.setImageResource(R.drawable.star_selected)
        }
        else {
            binding.wishlist.setImageResource(R.drawable.star)
        }

        binding.arrowBack.setOnClickListener {
            val newBooksJson = gson.toJson(updatableBooks)
            shared.edit().putString("books", newBooksJson).apply()
            findNavController().popBackStack()
        }

        binding.bookName.text = book.name
        binding.bookImage.setImageResource(book.img)
        binding.bookAuthorName.text = book.author
        binding.mb.text = book.size
        binding.genreName.text = book.genreName
        binding.pageCount.text = book.pages.toString()

        binding.saved.setOnClickListener {
            for (i in 0 until updatableBooks.size) {
                if (updatableBooks[i] == book) {
                    if (updatableBooks[i].isSaved) {
                        binding.saved.setImageResource(R.drawable.saved)
                        updatableBooks[i].isSaved = false
                    }
                    else {
                        binding.saved.setImageResource(R.drawable.saved_selected)
                        updatableBooks[i].isSaved = true
                        Toast.makeText(requireContext(), "Added to saved list", Toast.LENGTH_SHORT).show()
                    }
                    return@setOnClickListener
                }
            }
        }

        binding.wishlist.setOnClickListener {
            for (i in updatableBooks) {
                if (i == book) {
                    if (i.isWish) {
                        binding.wishlist.setImageResource(R.drawable.star)
                        i.isWish = false
                    }
                    else {
                        binding.wishlist.setImageResource(R.drawable.star_selected)
                        i.isWish = true
                        Toast.makeText(requireContext(), "Added to wishlist", Toast.LENGTH_SHORT).show()
                    }
                    return@setOnClickListener
                }
            }
        }

        binding.share.setOnClickListener {
            showBottomSheet()
        }

        return binding.root
    }

    private fun showBottomSheet() {
        val bottomSheetView = layoutInflater.inflate(R.layout.share_bottom_sheet, null)
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        dialog.setContentView(bottomSheetView)
        dialog.show()
    }

}