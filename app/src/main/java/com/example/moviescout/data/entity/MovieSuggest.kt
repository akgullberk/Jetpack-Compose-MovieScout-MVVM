package com.example.moviescout.data.entity

data class MovieSuggest(
    val success: Boolean,
    val result: List<Film>
)

data class Film(
    val url: String,
    val data: FilmData
)

data class FilmData(
    val lines: List<FilmLine>
)

data class FilmLine(
    val name: String,
    val year: String,
    val sty: String,
    val times: String,
    val dir: String,
    val sce: String,
    val pro: String,
    val pla: String,
    val teaser: String,
    val img: String
)

