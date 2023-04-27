package com.vmn.theschoolofrock.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vmn.domain.entity.CreditsEntity
import com.vmn.domain.entity.MovieDetailsEntity
import com.vmn.domain.entity.SimilarMoviesEntity
import com.vmn.domain.usecase.MovieDetailsUseCase
import com.vmn.domain.utils.Resource
import com.vmn.theschoolofrock.model.MovieDetailsBindingModel
import com.vmn.theschoolofrock.model.ResultCommon
import com.vmn.theschoolofrock.utils.Language
import com.vmn.theschoolofrock.utils.StringConfig
import com.vmn.theschoolofrock.utils.toString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsViewModel(
    private val useCase: MovieDetailsUseCase
) : ViewModel() {

    val commonResult = ObservableField<ResultCommon>()
    val movieDetailsBindingModel = MovieDetailsBindingModel()

    private var _moviesDetails = MutableLiveData<Resource<MovieDetailsEntity?>>()
    val moviesDetails: LiveData<Resource<MovieDetailsEntity?>>
        get() = _moviesDetails

    private var _credits = MutableLiveData<Resource<CreditsEntity?>>()
    val credits: LiveData<Resource<CreditsEntity?>>
        get() = _credits

    private var _similarMovies = MutableLiveData<Resource<SimilarMoviesEntity?>>()
    val similarMovies: LiveData<Resource<SimilarMoviesEntity?>>
        get() = _similarMovies


    fun getMovieDetails() {
        CoroutineScope(Dispatchers.IO).launch {
            showProgress()
            val response = useCase.getMovieDetails(commonResult.get()?.id ?: 0)

            withContext(Dispatchers.Main) {
                hideProgress()
                when (response) {
                    is Resource.Success -> {
                        _moviesDetails.postValue(Resource.Success(response.data))
                        response.data?.let {
                            mapData(it)
                        }
                    }

                    is Resource.Error -> {
                        _moviesDetails.postValue(Resource.Error(response.errorMessage))
                    }

                    Resource.NoInternet -> {
                        _moviesDetails.postValue(Resource.NoInternet)
                    }

                    Resource.Unknown -> {
                        _moviesDetails.postValue(Resource.Unknown)
                    }
                }
            }
        }
    }

    fun getCredits() {
        CoroutineScope(Dispatchers.IO).launch {
            showProgress()
            val response = useCase.getCredits(commonResult.get()?.id ?: 0)

            withContext(Dispatchers.Main) {
                hideProgress()
                when (response) {
                    is Resource.Success -> {
                        _credits.postValue(Resource.Success(response.data))
                    }

                    is Resource.Error -> {
                        _credits.postValue(Resource.Error(response.errorMessage))
                    }

                    Resource.NoInternet -> {
                        _credits.postValue(Resource.NoInternet)
                    }

                    Resource.Unknown -> {
                        _credits.postValue(Resource.Unknown)
                    }
                }
            }
        }
    }

    fun getSimilarMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            showProgress()
            val response = useCase.getSimilarMovies(commonResult.get()?.id ?: 0)

            withContext(Dispatchers.Main) {
                hideProgress()
                when (response) {
                    is Resource.Success -> {
                        _similarMovies.postValue(Resource.Success(response.data))
                    }

                    is Resource.Error -> {
                        _similarMovies.postValue(Resource.Error(response.errorMessage))
                    }

                    Resource.NoInternet -> {
                        _similarMovies.postValue(Resource.NoInternet)
                    }

                    Resource.Unknown -> {
                        _similarMovies.postValue(Resource.Unknown)
                    }
                }
            }
        }
    }


    private fun mapData(details: MovieDetailsEntity) {
        movieDetailsBindingModel.apply {
            genre =
                details.genres toString StringConfig(MovieDetailsEntity.Genre::name.name, "| ")
            originalLanguage =
                Language.fromCode(details.originalLanguage)?.lang ?: details.originalLanguage

            overview =  details.overview
            releaseDate = details.releaseDate
            voteAverage = (details.voteAverage / 2)
            voteCount = details.voteCount

        }

    }

    private fun showProgress() {
        movieDetailsBindingModel.showProgress = true
    }

    private fun hideProgress() {
        movieDetailsBindingModel.showProgress = false
    }
}