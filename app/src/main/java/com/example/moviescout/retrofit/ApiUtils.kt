package com.example.moviescout.retrofit

class ApiUtils {
    companion object{
        val BASE_URL = "https://api.collectapi.com/"

        fun getMovieSuggestDao() : MovieSuggestDao{
            return RetrofitClient.getClient(BASE_URL).create(MovieSuggestDao::class.java)
        }

    }
}