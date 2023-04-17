package com.example.medical.model

data class Book(var name : String,
                var author : String,
                var pages : Int,
                var description : String,
                var rating : Double,
                var size : String,
                var price : String,
                var img : Int,
                var genres : ArrayList<Genre>?,
                var reviews : ArrayList<Review>?)
