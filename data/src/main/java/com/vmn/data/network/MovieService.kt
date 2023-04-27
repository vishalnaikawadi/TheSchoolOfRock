package com.vmn.data.network

import com.vmn.data.model.CreditsModel
import com.vmn.data.model.MovieDetailsModel
import com.vmn.data.model.MoviesNowPlayingModel
import com.vmn.data.model.SimilarMoviesModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("/3/movie/now_playing")
    suspend fun getListOfMoviesCurrentlyPlaying(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String? = "en-US"
    ): MoviesNowPlayingModel?

    @GET("/3/movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String? = "en-US"
    ): MovieDetailsModel?

    @GET("/3/movie/{movieId}/credits")
    suspend fun getCredits(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String? = "en-US"
    ): CreditsModel?

    @GET("/3/movie/{movieId}/similar")
    suspend fun getSimilarMovies(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String? = "en-US"
    ): SimilarMoviesModel?
}