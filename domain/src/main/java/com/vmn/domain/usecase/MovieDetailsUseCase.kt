package com.vmn.domain.usecase

import com.vmn.domain.entity.CreditsEntity
import com.vmn.domain.entity.MovieDetailsEntity
import com.vmn.domain.entity.SimilarMoviesEntity
import com.vmn.domain.repo.MovieRepo
import com.vmn.domain.utils.Resource

interface MovieDetailsUseCase {

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

class MovieDetailsUseCaseImpl(
    private val repo: MovieRepo
) : MovieDetailsUseCase {

    override suspend fun getMovieDetails(
        movieId: Int,
        language: String?
    ): Resource<MovieDetailsEntity?> {
        return repo.getMovieDetails(movieId, language)
    }

    override suspend fun getCredits(movieId: Int, language: String?): Resource<CreditsEntity?> {
        return repo.getCredits(movieId, language)
    }

    override suspend fun getSimilarMovies(
        movieId: Int,
        language: String?
    ): Resource<SimilarMoviesEntity?> {
        return repo.getSimilarMovies(movieId, language)
    }
}