package com.vmn.domain.di

import com.vmn.domain.usecase.MovieDetailsUseCase
import com.vmn.domain.usecase.MovieDetailsUseCaseImpl
import com.vmn.domain.usecase.MoviesNowPlayingUseCase
import com.vmn.domain.usecase.MoviesNowPlayingUseCaseImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val domainModule = module {

    //using single for useCase because it is stateless and can be shared across the app
    single { MoviesNowPlayingUseCaseImpl(get()) } bind MoviesNowPlayingUseCase::class
    single { MovieDetailsUseCaseImpl(get()) } bind MovieDetailsUseCase::class
}