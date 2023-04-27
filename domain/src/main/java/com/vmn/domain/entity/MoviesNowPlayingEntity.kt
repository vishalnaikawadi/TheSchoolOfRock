package com.vmn.domain.entity

import androidx.annotation.Keep

@Keep
data class MoviesNowPlayingEntity(
    val dates: Dates?,
    val page: Int = 0,
    val results: List<Result> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
) {
    @Keep
    data class Dates(
        val maximum: String = "",
        val minimum: String = ""
    )

    @Keep
    data class Result(
        val adult: Boolean = false,
        val backdropPath: String = "",
        val genreIds: List<Int> = listOf(),
        val id: Int = 0,
        val originalLanguage: String = "",
        val originalTitle: String = "",
        val overview: String = "",
        val popularity: Double = 0.0,
        val posterPath: String = "",
        val releaseDate: String = "",
        val title: String = "",
        val video: Boolean = false,
        val voteAverage: Double = 0.0,
        val voteCount: Int = 0
    )
}