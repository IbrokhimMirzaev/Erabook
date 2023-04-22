package com.example.medical.books

import android.content.Context
import com.example.medical.R
import com.example.medical.model.Book
import com.google.gson.Gson

class BookApi(context: Context) {
    companion object {
        private var instance: BookApi? = null
        fun newInstance(context: Context): BookApi {
            if (instance == null) {
                instance = BookApi(context)
            }
            return instance!!
        }
    }

    private val shared = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveAllBooksToShared() {
        var books = ArrayList<Book>()

        books.add(
            Book(
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
            )
        )

        books.add(
            Book(
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
            )
        )

        books.add(
            Book(
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
            )
        )

        books.add(
            Book(
                "The Vallentine's Hate",
                "Sidney Halston",
                235,
                "\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
                4.1,
                "5.6 mb",
                "$10.50",
                R.drawable.valentines,
                "Romance",
                null
            )
        )

        books.add(
            Book(
                "Harry Potter",
                "J.K. Rowling",
                235,
                "\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
                4.2,
                "6.9 mb",
                "$8.50",
                R.drawable.harry_potter2,
                "Action",
                null
            )
        )

        books.add(
            Book(
                "Keeper of Secrets",
                "Denise Grover",
                235,
                "\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
                4.3,
                "5.6 mb",
                "$11.50",
                R.drawable.img_3,
                "Romance",
                null
            )
        )

        val booksJson = gson.toJson(books)
        shared.edit().putString("books", booksJson).apply()
    }
}