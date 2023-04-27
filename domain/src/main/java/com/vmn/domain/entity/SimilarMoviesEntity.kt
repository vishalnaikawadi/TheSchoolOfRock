package com.vmn.domain.entity

import androidx.annotation.Keep

@Keep
data class SimilarMoviesEntity(
    val page: Int,
    val results: List<Result>
) {
    data class Result(
        val id: Int,
        val posterPath: String,
        val releaseDate: String,
        val title: String,
        val voteAverage: Float,
        val voteCount: Int
    )
}
