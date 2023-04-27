package com.vmn.data.utils

import com.vmn.data.model.CreditsModel
import com.vmn.data.model.MovieDetailsModel
import com.vmn.data.model.MoviesNowPlayingModel
import com.vmn.data.model.SimilarMoviesModel
import com.vmn.domain.entity.CreditsEntity
import com.vmn.domain.entity.MovieDetailsEntity
import com.vmn.domain.entity.MoviesNowPlayingEntity
import com.vmn.domain.entity.SimilarMoviesEntity


fun MoviesNowPlayingModel.map() = MoviesNowPlayingEntity(
    dates = dates?.let {
        MoviesNowPlayingEntity.Dates(
            maximum = it.maximum.orEmpty(),
            minimum = it.minimum.orEmpty()
        )
    },
    page = page ?: 0,
    results = results?.map {
        MoviesNowPlayingEntity.Result(
            adult = it.adult ?: false,
            backdropPath = it.backdrop_path.orEmpty(),
            genreIds = it.genre_ids.orEmpty(),
            id = it.id ?: 0,
            originalLanguage = it.original_language.orEmpty(),
            originalTitle = it.original_title.orEmpty(),
            overview = it.overview.orEmpty(),
            popularity = it.popularity ?: 0.0,
            posterPath = it.poster_path.orEmpty(),
            releaseDate = it.release_date.orEmpty(),
            title = it.title.orEmpty(),
            video = it.video ?: false,
            voteAverage = it.vote_average ?: 0.0,
            voteCount = it.vote_count ?: 0

        )
    } ?: emptyList(),
    totalPages = total_pages ?: 0,
    totalResults = total_results ?: 0
)

fun MovieDetailsModel.map() = MovieDetailsEntity(
    backdropPath = backdropPath.orEmpty(),
    budget = budget ?: 0,
    genres = genres?.map {
        MovieDetailsEntity.Genre(
            id = it.id ?: 0,
            name = it.name.orEmpty(),
        )
    }.orEmpty(),
    homepage = homepage.orEmpty(),
    id = id ?: 0,
    imdbId = imdbId.orEmpty(),
    originalLanguage = originalLanguage ?: "en",
    originalTitle = originalTitle.orEmpty(),
    overview = overview.orEmpty(),
    popularity = popularity ?: 0.0,
    releaseDate = releaseDate ?: "N/A",
    revenue = revenue ?: 0,
    runtime = runtime ?: 0,
    status = status.orEmpty(),
    tagline = tagline.orEmpty(),
    title = title.orEmpty(),
    video = video ?: false,
    voteAverage = voteAverage ?: 0f,
    voteCount = voteCount ?: 0
)

fun CreditsModel.map() = CreditsEntity(
    cast = cast?.map {
        CreditsEntity.Cast(
            character = it.character.orEmpty(),
            gender = it.gender ?: 0,
            id = it.id ?: 0,
            knownForDepartment = it.knownForDepartment.orEmpty(),
            name = it.name.orEmpty(),
            profilePath = it.profilePath.orEmpty()
        )
    }.orEmpty(),
    id = id ?: 0
)

fun SimilarMoviesModel.map() = SimilarMoviesEntity(
    page = page ?: 0,
    results = results?.map {
        SimilarMoviesEntity.Result(
            id = it.id ?: 0,
            posterPath = it.posterPath.orEmpty(),
            releaseDate = it.releaseDate.orEmpty(),
            title = it.title.orEmpty(),
            voteAverage = it.voteAverage ?: 0f,
            voteCount = it.voteCount ?: 0
        )
    }.orEmpty()
)