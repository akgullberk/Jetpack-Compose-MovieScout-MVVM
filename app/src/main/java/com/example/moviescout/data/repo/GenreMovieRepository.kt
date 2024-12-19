package com.example.moviescout.data.repo

import com.example.moviescout.data.datasource.GenreMovieDataSource
import com.example.moviescout.data.entity.Movie
import com.example.moviescout.data.entity.MovieResponse

class GenreMovieRepository(var gmds : GenreMovieDataSource) {

    fun getGenreMovie(genreId: Int, callback: (List<Movie>) -> Unit, errorCallback: (String) -> Unit){
        gmds.fetchMoviesByGenre(genreId,callback,errorCallback)
    }
}