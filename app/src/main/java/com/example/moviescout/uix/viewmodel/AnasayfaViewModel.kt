package com.example.moviescout.uix.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviescout.data.entity.Film
import com.example.moviescout.data.entity.Movie
import com.example.moviescout.data.entity.MovieResponse
import com.example.moviescout.data.repo.GenreMovieRepository
import com.example.moviescout.data.repo.MovieSuggestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor(var msrepo : MovieSuggestRepository,var gmrepo : GenreMovieRepository) : ViewModel() {

    private val _movieSuggestList = MutableLiveData<List<Film>>()
    val movieSuggestList: LiveData<List<Film>> get() = _movieSuggestList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> get() = _movieList



    fun fetchMovieSuggest(){
        msrepo.getMovieSuggest({ Film ->
            _movieSuggestList.postValue(Film)

        }, { error ->
            _errorMessage.postValue(error)
        })
    }

    fun fetchMoviesByGenre(genreId: Int) {
        gmrepo.getGenreMovie(
            genreId = genreId,
            callback = { movies ->
                _movieList.postValue(movies) // Filmleri listeye aktar
            },
            errorCallback = { error ->
                _errorMessage.postValue(error) // Hata mesajını aktar
            }
        )
    }
}