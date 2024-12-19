package com.example.moviescout.retrofit

import com.example.moviescout.data.entity.MovieResponse
import com.example.moviescout.data.entity.MovieSuggest
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query

interface MovieSuggestDao {
    @GET("watching/movieSuggest")
    fun getMovieSuggest() : Call<MovieSuggest>

}

interface GenreMovieDao{
    @GET("discover/movie")
    fun getGenreMovie(
        @Query("language") language: String = "tr",
        @Query("with_genres") genreId: Int // Dinamik olarak genreId parametresi alıyoruz
    ): Call<MovieResponse>
}