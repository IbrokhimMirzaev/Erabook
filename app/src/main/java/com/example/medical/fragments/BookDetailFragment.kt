package com.example.medical.fragments

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.medical.R
import com.example.medical.databinding.FragmentBookDetailBinding
import com.example.medical.model.Book
import com.example.medical.model.User
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.logging.Handler
import kotlin.math.log

class BookDetailFragment : Fragment() {
    lateinit var binding: FragmentBookDetailBinding
    lateinit var shared : SharedPreferences
    lateinit var updatableBooks : ArrayList<Book>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookDetailBinding.inflate(inflater, container, false)

        shared = requireContext().getSharedPreferences("shared", Context.MODE_PRIVATE)
        val gson = Gson()
        val booksJson = shared.getString("books", null)
        val books =
            gson.fromJson<ArrayList<Book>>(booksJson, object : TypeToken<ArrayList<Book>>() {}.type)

        var book = arguments?.getSerializable("book") as Book

        updatableBooks = books

        if (book.isSaved) {
            binding.saved.setImageResource(R.drawable.saved_selected)
        } else {
            binding.saved.setImageResource(R.drawable.saved)
        }

        if (book.isWish) {
            binding.wishlist.setImageResource(R.drawable.star_selected)
        } else {
            binding.wishlist.setImageResource(R.drawable.star)
        }

        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.bookName.text = book.name
        binding.bookImage.setImageResource(book.img)
        binding.bookAuthorName.text = book.author
        binding.mb.text = book.size
        binding.genreName.text = book.genreName
        binding.pageCount.text = book.pages.toString()

        binding.saved.setOnClickListener {
            changeBookStatus(true, updatableBooks, book)
            val newBooksJson = gson.toJson(updatableBooks)
            shared.edit().putString("books", newBooksJson).apply()
            updatableBooks =
                gson.fromJson(newBooksJson, object : TypeToken<ArrayList<Book>>() {}.type)
        }

        binding.wishlist.setOnClickListener {
            changeBookStatus(false, updatableBooks, book)
            val newBooksJson = gson.toJson(updatableBooks)
            shared.edit().putString("books", newBooksJson).apply()
            updatableBooks =
                gson.fromJson(newBooksJson, object : TypeToken<ArrayList<Book>>() {}.type)
        }

        binding.share.setOnClickListener {
            showBottomSheet()
        }

        binding.shareButton.setOnClickListener {
            showBottomSheet()
        }
        val userJson = shared.getString("active_user", null)
        val user: User = gson.fromJson(userJson, User::class.java)
        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            binding.rate.visibility = View.VISIBLE
            book.rating = rating.toDouble()
        }

        val dialogBinding = layoutInflater.inflate(R.layout.my_custom2, null)
        val myDialog = Dialog(requireContext())
        myDialog.setContentView(dialogBinding)
        myDialog.setCancelable(true)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.submitBtn.setOnClickListener {
            book.review = binding.comment.text.toString()
            myDialog.show()
            android.os.Handler(Looper.getMainLooper()).postDelayed({
                myDialog.dismiss()
            }, 1500)
            binding.rate.visibility = View.GONE
            binding.endcommentlayout.visibility = View.VISIBLE
            binding.userName.setText(user.username)
            binding.myrating.text = book.rating.toString()
            binding.endcomment.text = book.review
            binding.ratingBar.rating = book.rating.toFloat()
            changeDescriptionStatus(updatableBooks, book)
        }

        if (book.review != null) {
            binding.endcommentlayout.visibility = View.VISIBLE
            binding.rate.visibility = View.GONE
            binding.userName.setText(user.username)
            binding.myrating.text = book.rating.toString()
            binding.endcomment.text = book.review
            binding.ratingBar.rating = book.rating.toFloat()
            binding.ratingBar.setIsIndicator(true)
        }

        return binding.root
    }

    private fun showBottomSheet() {
        val bottomSheetView = layoutInflater.inflate(R.layout.share_bottom_sheet, null)
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        dialog.setContentView(bottomSheetView)
        dialog.show()
    }

    private fun changeBookStatus(forSaved: Boolean, updatableBooks: ArrayList<Book>, book: Book) {
        for (i in updatableBooks) {
            if (i == book) {
                if (forSaved) {
                    if (i.isSaved) {
                        binding.saved.setImageResource(R.drawable.saved)
                        i.isSaved = false
                        book.isSaved = false
                    } else {
                        binding.saved.setImageResource(R.drawable.saved_selected)
                        i.isSaved = true
                        book.isSaved = true
                        Toast.makeText(requireContext(), "Added to saved", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    if (i.isWish) {
                        binding.wishlist.setImageResource(R.drawable.star)
                        i.isWish = false
                        book.isWish = false
                    } else {
                        binding.wishlist.setImageResource(R.drawable.star_selected)
                        i.isWish = true
                        book.isWish = true
                        Toast.makeText(requireContext(), "Added to wishlist", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun changeDescriptionStatus(books : ArrayList<Book>, book: Book) {
        for (i in books) {
            if (i == book) {
                if (book.review != null) {
                    i.review = book.review
                    val newBooksJson = Gson().toJson(updatableBooks)
                    shared.edit().putString("books", newBooksJson).apply()
                    updatableBooks =
                        Gson().fromJson(newBooksJson, object : TypeToken<ArrayList<Book>>() {}.type)
                }
            }
        }
    }
}