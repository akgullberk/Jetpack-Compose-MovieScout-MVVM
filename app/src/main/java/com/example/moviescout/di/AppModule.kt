package com.example.moviescout.di

import com.example.moviescout.data.datasource.MovieSuggestDataSource
import com.example.moviescout.data.repo.MovieSuggestRepository
import com.example.moviescout.retrofit.MovieSuggestDao
import com.example.moviescout.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideHaberlerDao(retrofit: Retrofit): MovieSuggestDao {
        return retrofit.create(MovieSuggestDao::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieSuggestDataSource(msdao : MovieSuggestDao) : MovieSuggestDataSource{
        return MovieSuggestDataSource(msdao)
    }

    @Provides
    @Singleton
    fun provideMovieSuggestRepository(msds : MovieSuggestDataSource) : MovieSuggestRepository{
        return MovieSuggestRepository(msds)
    }

    // Retrofit Client'ı sağlamak için
    @Provides
    @Singleton
    fun provideRetrofitClient(): Retrofit {
        return RetrofitClient.getClient("https://api.collectapi.com/")
    }
}