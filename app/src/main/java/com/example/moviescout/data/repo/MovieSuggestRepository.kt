package com.example.moviescout.data.repo

import com.example.moviescout.data.datasource.MovieSuggestDataSource
import com.example.moviescout.data.entity.Film

class MovieSuggestRepository(var msds: MovieSuggestDataSource) {

    fun getMovieSuggest(callback: (List<Film>) -> Unit, errorCallback: (String) -> Unit){
        msds.fetchMovieSuggest(callback,errorCallback)
    }
}