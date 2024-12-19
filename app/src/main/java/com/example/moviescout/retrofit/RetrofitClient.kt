package com.example.moviescout.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        fun getClient(baseUrl:String, interceptor: Interceptor) : Retrofit {
            // OkHttpClient'e interceptor ekleniyor
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor) // ApiKeyInterceptor burada ekleniyor
                .build()


            return  Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client) // Interceptor'ü içeren OkHttpClient kullanılıyor
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }


    }
}