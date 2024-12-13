package com.example.moviescout.data.datasource

import android.util.Log
import com.example.moviescout.data.entity.Film
import com.example.moviescout.data.entity.FilmLine
import com.example.moviescout.data.entity.MovieSuggest
import com.example.moviescout.retrofit.MovieSuggestDao
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call

class MovieSuggestDataSource(var msdao : MovieSuggestDao) {

    // API'den haberleri çekmek için method
    fun fetchMovieSuggest(callback: (List<Film>) -> Unit, errorCallback: (String) -> Unit) {
        msdao.getMovieSuggest().enqueue(object : Callback<MovieSuggest> {
            override fun onResponse(call: Call<MovieSuggest>, response: Response<MovieSuggest>) {
                if (response.isSuccessful) {
                    // response.body() içinde gelen veri, `Haberler` türünde olacak, bu yüzden
                    // `Haberler`'in `result` özelliğinden `List<HaberlerItem>` alınır.
                    response.body()?.result?.let {
                        callback(it) // HaberlerItem listesi döndürülüyor
                    } ?: errorCallback("No data found")
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    errorCallback("Failed response code: ${response.code()}, message: $errorBody")
                    Log.e("API_ERROR", "Code: ${response.code()}, Body: $errorBody")
                }
            }

            override fun onFailure(call: Call<MovieSuggest>, t: Throwable) {
                errorCallback("Error: ${t.message}")

            }
        })
    }
}