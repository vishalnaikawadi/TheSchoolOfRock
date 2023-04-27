package com.vmn.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SimilarMoviesModel(
    val page: Int?,
    val results: List<Result>?
) {
    data class Result(
        val id: Int?,
        @SerializedName("poster_path")
        val posterPath: String?,
        @SerializedName("release_date")
        val releaseDate: String?,
        val title: String?,
        @SerializedName("vote_average")
        val voteAverage: Float?,
        @SerializedName("vote_count")
        val voteCount: Int?
    )
}