package com.example.moviescout.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "apikey 63rzaUT6tMCYIBXrqai0Im:3iMHvTKDpLs1OKX5MQaPWB")
            .addHeader("Content-Type", "application/json")
            .build()
        return chain.proceed(newRequest)
    }
}