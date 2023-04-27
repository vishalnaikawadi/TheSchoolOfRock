package com.vmn.data

import com.vmn.data.network.MovieService
import com.vmn.data.utils.NetworkManager
import com.vmn.data.utils.map
import com.vmn.domain.entity.CreditsEntity
import com.vmn.domain.entity.MovieDetailsEntity
import com.vmn.domain.entity.MoviesNowPlayingEntity
import com.vmn.domain.entity.SimilarMoviesEntity
import com.vmn.domain.repo.MovieRepo
import com.vmn.domain.utils.Resource

class MovieRepoImpl(
    private val service: MovieService,
    private val networkManager: NetworkManager
) : MovieRepo {

    override suspend fun getListMoviesNowPlaying(
        page: Int,
        language: String?
    ): Resource<MoviesNowPlayingEntity?> {

        return if (networkManager.isNetworkAvailable()) {

            try {
                val response =
                    service.getListOfMoviesCurrentlyPlaying(
                        apiKey = BuildConfig.KEY,
                        page = page
                    )

                response?.let {
                    Resource.Success(it.map())
                } ?: run {
                    Resource.Error("No new data available")
                }

            } catch (ex: Exception) {
                Resource.Unknown
            }

        } else {
            Resource.NoInternet
        }
    }

    override suspend fun getMovieDetails(
        movieId: Int,
        language: String?
    ): Resource<MovieDetailsEntity?> {
        return if (networkManager.isNetworkAvailable()) {

            try {
                val response =
                    service.getMovieDetails(
                        apiKey = BuildConfig.KEY,
                        movieId = movieId
                    )

                response?.let {
                    Resource.Success(it.map())
                } ?: run {
                    Resource.Error("No new data available")
                }

            } catch (ex: Exception) {
                println(ex)
                Resource.Unknown
            }

        } else {
            Resource.NoInternet
        }

    }

    override suspend fun getCredits(movieId: Int, language: String?): Resource<CreditsEntity?> {
        return if (networkManager.isNetworkAvailable()) {

            try {
                val response =
                    service.getCredits(
                        apiKey = BuildConfig.KEY,
                        movieId = movieId
                    )

                response?.let {
                    Resource.Success(it.map())
                } ?: run {
                    Resource.Error("No new data available")
                }

            } catch (ex: Exception) {
                println(ex)
                Resource.Unknown
            }

        } else {
            Resource.NoInternet
        }

    }

    override suspend fun getSimilarMovies(
        movieId: Int,
        language: String?
    ): Resource<SimilarMoviesEntity?> {
        return if (networkManager.isNetworkAvailable()) {

            try {
                val response =
                    service.getSimilarMovies(
                        apiKey = BuildConfig.KEY,
                        movieId = movieId
                    )

                response?.let {
                    Resource.Success(it.map())
                } ?: run {
                    Resource.Error("No new data available")
                }

            } catch (ex: Exception) {
                println(ex)
                Resource.Unknown
            }

        } else {
            Resource.NoInternet
        }

    }


}