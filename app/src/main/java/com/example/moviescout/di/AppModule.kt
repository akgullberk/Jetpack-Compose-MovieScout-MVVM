package com.example.moviescout.di

import com.example.moviescout.retrofit.MovieSuggestDao
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
}