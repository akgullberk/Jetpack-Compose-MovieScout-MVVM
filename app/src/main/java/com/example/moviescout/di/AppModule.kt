package com.example.moviescout.di

import com.example.moviescout.data.datasource.GenreMovieDataSource
import com.example.moviescout.data.datasource.MovieSuggestDataSource
import com.example.moviescout.data.repo.GenreMovieRepository
import com.example.moviescout.data.repo.MovieSuggestRepository
import com.example.moviescout.retrofit.ApiKeyInterceptor
import com.example.moviescout.retrofit.ApiKeyInterceptor2
import com.example.moviescout.retrofit.GenreMovieDao
import com.example.moviescout.retrofit.MovieSuggestDao
import com.example.moviescout.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideMovieSuggestDao(@Named("MovieSuggest") retrofit: Retrofit): MovieSuggestDao {
        return retrofit.create(MovieSuggestDao::class.java)
    }

    @Provides
    @Singleton
    fun provideGenreMovieDao(@Named("GenreMovie") retrofit: Retrofit): GenreMovieDao {
        return retrofit.create(GenreMovieDao::class.java)
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

    @Provides
    @Singleton
    fun provideGenreMovieDataSource(gmdao: GenreMovieDao) : GenreMovieDataSource{
        return GenreMovieDataSource(gmdao)
    }

    @Provides
    @Singleton
    fun provideGenreMovieRepository(gmds : GenreMovieDataSource) : GenreMovieRepository {
        return GenreMovieRepository(gmds)
    }


    @Provides
    @Singleton
    @Named("MovieSuggest")
    fun provideMovieSuggestRetrofit(): Retrofit {
        return RetrofitClient.getClient(
            baseUrl = "https://api.collectapi.com/",
            interceptor = ApiKeyInterceptor()
        )
    }

    @Provides
    @Singleton
    @Named("GenreMovie")
    fun provideGenreMovieRetrofit(): Retrofit {
        return RetrofitClient.getClient(
            baseUrl = "https://api.themoviedb.org/3/",
            interceptor = ApiKeyInterceptor2()
        )
    }
}