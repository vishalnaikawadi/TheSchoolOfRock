package com.vmn.theschoolofrock.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vmn.domain.entity.MoviesNowPlayingEntity
import com.vmn.domain.usecase.MoviesNowPlayingUseCase
import com.vmn.domain.utils.Resource
import com.vmn.theschoolofrock.model.MoviesPlayingBindingModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesPlayingNowViewModel(
    private val useCase: MoviesNowPlayingUseCase
) : ViewModel() {

    var moviesPlayingNowBinding = MoviesPlayingBindingModel()

    private var _moviesPlaying = MutableLiveData<Resource<MoviesNowPlayingEntity?>>()
    val moviesPlaying: LiveData<Resource<MoviesNowPlayingEntity?>>
        get() = _moviesPlaying

    private var _moviePlayingResults = mutableListOf<MoviesNowPlayingEntity.Result>()
    val moviePlayingResults: List<MoviesNowPlayingEntity.Result>
        get() = _moviePlayingResults

    private var _pageNum = 1
    private var _totalPages = 1

    fun getListMoviesNowPlaying() {

        CoroutineScope(Dispatchers.IO).launch {
            showProgress()
            val response = useCase.getListMoviesNowPlaying(_pageNum)

            withContext(Dispatchers.Main) {

                hideProgress()
                when (response) {
                    is Resource.Success -> {

                        _moviePlayingResults.clear()
                        _moviePlayingResults.addAll(response.data?.results.orEmpty())
                        _pageNum++
                        _totalPages = response.data?.totalPages ?: 0
                        _moviesPlaying.postValue(Resource.Success(response.data))
                    }

                    is Resource.Error -> {

                        _moviesPlaying.postValue(Resource.Error(response.errorMessage))
                    }

                    Resource.NoInternet -> {

                        _moviesPlaying.postValue(Resource.NoInternet)
                    }

                    Resource.Unknown -> {

                        _moviesPlaying.postValue(Resource.Unknown)
                    }
                }
            }
        }
    }

    fun isNextPageAvailable() = _pageNum < _totalPages

    private fun showProgress() {
        moviesPlayingNowBinding.showProgress = true
    }

    private fun hideProgress() {
        moviesPlayingNowBinding.showProgress = false
    }

}