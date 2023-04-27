package com.vmn.theschoolofrock.di

import com.vmn.theschoolofrock.viewmodel.MovieDetailsViewModel
import com.vmn.theschoolofrock.viewmodel.MoviesPlayingNowViewModel
import com.vmn.theschoolofrock.viewmodel.ReviewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { MoviesPlayingNowViewModel(get()) }
    viewModel { MovieDetailsViewModel(get()) }
    viewModel { ReviewsViewModel() }
}