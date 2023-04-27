package com.vmn.domain.repo

import com.vmn.domain.entity.CreditsEntity
import com.vmn.domain.entity.MovieDetailsEntity
import com.vmn.domain.entity.MoviesNowPlayingEntity
import com.vmn.domain.entity.SimilarMoviesEntity
import com.vmn.domain.utils.Resource

interface MovieRepo {

    suspend fun getListMoviesNowPlaying(
        page: Int,
        language: String? = null
    ): Resource<MoviesNowPlayingEntity?>

    suspend fun getMovieDetails(
        movieId: Int,
        language: String? = null
    ): Resource<MovieDetailsEntity?>

    suspend fun getCredits(
        movieId: Int,
        language: String? = null
    ): Resource<CreditsEntity?>

    suspend fun getSimilarMovies(
        movieId: Int,
        language: String? = null
    ): Resource<SimilarMoviesEntity?>
}