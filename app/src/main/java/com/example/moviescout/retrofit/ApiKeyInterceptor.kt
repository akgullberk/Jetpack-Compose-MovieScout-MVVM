package com.example.moviescout.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "apikey 6Y0903Bi4wUFkLhtdnoR1G:2Lyz3f5QYJoqVU7KL1F4HI")
            .addHeader("Content-Type", "application/json")
            .build()
        return chain.proceed(newRequest)
    }


}