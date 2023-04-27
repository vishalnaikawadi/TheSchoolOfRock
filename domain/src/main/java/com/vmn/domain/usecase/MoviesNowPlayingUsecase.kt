package com.vmn.domain.usecase

import com.vmn.domain.entity.MovieDetailsEntity
import com.vmn.domain.entity.MoviesNowPlayingEntity
import com.vmn.domain.repo.MovieRepo
import com.vmn.domain.utils.Resource

interface MoviesNowPlayingUseCase {

    suspend fun getListMoviesNowPlaying(
        page: Int,
        language: String? = null
    ): Resource<MoviesNowPlayingEntity?>

}

class MoviesNowPlayingUseCaseImpl(private val repo: MovieRepo) : MoviesNowPlayingUseCase {
    override suspend fun getListMoviesNowPlaying(
        page: Int,
        language: String?
    ): Resource<MoviesNowPlayingEntity?> {
        return repo.getListMoviesNowPlaying(page, language)
    }

}