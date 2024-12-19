package com.example.moviescout.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor2 : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzN2U1ZDg0NjEzM2UyYzM5N2Y4NzQ1MWE2ZTIxN2EzMiIsIm5iZiI6MTczNDAyMzc0Ny43ODcwMDAyLCJzdWIiOiI2NzViMWE0M2E3YWJjYzM4ZDEzODdiNTEiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.W4FRWOUMs5uFgNACGm4HeA2diS3755vBXGTAwlYcCAg")
            .addHeader("accept", "application/json")
            .build()
        return chain.proceed(newRequest)
    }
}