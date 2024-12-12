package com.example.moviescout.retrofit

import com.example.moviescout.data.entity.MovieSuggest
import retrofit2.http.GET
import retrofit2.Call

interface MovieSuggestDao {
    @GET("watching/movieSuggest")
    fun getMovieSuggest() : Call<MovieSuggest>

}