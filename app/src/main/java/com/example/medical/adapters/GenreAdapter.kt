package com.example.medical.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medical.R
import com.example.medical.model.Genre
import com.google.android.material.imageview.ShapeableImageView

class GenreAdapter(var genres : ArrayList<Genre>) : RecyclerView.Adapter<GenreAdapter.MyHolder>() {
    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.genreText)
        var img = itemView.findViewById<ShapeableImageView>(R.id.genreImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.genre_item, parent, false))
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var genre = genres[position]
        holder.name.text = genre.name
        holder.img.setImageResource(genre.img)
    }
}