package com.example.moviescout.data.entity


// API'den dönen filmler listesi ve sayfa bilgilerini temsil eden data class
data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)

// Filmin detaylarını temsil eden data class
data class Movie(
    val id: Int,
    val title: String,
    val original_language: String,
    val overview: String,
    val release_date: String,
    val vote_average: Double,
    val vote_count: Int,
    val backdrop_path: String?,
    val poster_path : String?
)


