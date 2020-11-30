package com.example.moviesrxjava.model

data class MoviesResponse (
    val title :String,
    val image :String,
    val rating: Float,
    val releaseYear:Int,
    val genre: List<String>
)
