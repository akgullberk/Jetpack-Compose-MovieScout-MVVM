package com.example.moviescout.data.datasource

import com.example.moviescout.data.entity.MovieResponse
import com.example.moviescout.retrofit.GenreMovieDao
import android.util.Log
import com.example.moviescout.data.entity.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenreMovieDataSource(var gmdao: GenreMovieDao) {

    fun fetchMoviesByGenre(
        genreId: Int,
        callback: (List<Movie>) -> Unit,
        errorCallback: (String) -> Unit
    ) {
        gmdao.getGenreMovie("tr",genreId).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: retrofit2.Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val movieList = response.body()?.results // MovieResponse'dan results'ı al
                    if (movieList != null) {
                        callback(movieList) // results listesini geri döndür
                    } else {
                        errorCallback("No movies found")
                    }
                } else {
                    errorCallback("API Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                errorCallback("Network Error: ${t.message}")
                Log.e("NETWORK_ERROR", "Error: ${t.message}")
            }
        })
    }
}

