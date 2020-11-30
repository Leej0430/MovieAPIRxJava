package com.example.moviesrxjava.model

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
//https://api.androidhive.info/json/movies.json

const val END_POINT = "json/movies.json"
const val BASE_URL = "https://api.androidhive.info/"

interface MovieAPI {
    @GET(END_POINT)
    fun getMovies():Observable<List<MoviesResponse>>
}