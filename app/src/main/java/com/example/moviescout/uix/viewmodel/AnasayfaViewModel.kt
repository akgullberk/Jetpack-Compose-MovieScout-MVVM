package com.example.moviescout.uix.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviescout.data.entity.Film
import com.example.moviescout.data.repo.MovieSuggestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor(var msrepo : MovieSuggestRepository) : ViewModel() {

    private val _movieSuggestList = MutableLiveData<List<Film>>()
    val movieSuggestList: LiveData<List<Film>> get() = _movieSuggestList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchMovieSuggest(){
        msrepo.getMovieSuggest({ Film ->
            _movieSuggestList.postValue(Film)

        }, { error ->
            _errorMessage.postValue(error)
        })
    }

}