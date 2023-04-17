package com.example.medical.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medical.R
import com.example.medical.model.Book
import com.google.android.material.imageview.ShapeableImageView

class BookAdapter(var books : ArrayList<Book>) : RecyclerView.Adapter<BookAdapter.MyHolder>() {
    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.bookName)
        var img = itemView.findViewById<ShapeableImageView>(R.id.bookImg)
        var price = itemView.findViewById<TextView>(R.id.bookPrice)
        var rating = itemView.findViewById<TextView>(R.id.bookRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false))
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var book = books[position]
        holder.name.text = book.name
        holder.img.setImageResource(book.img)
        holder.price.text = book.price
        holder.rating.text = book.rating.toString()
    }
}