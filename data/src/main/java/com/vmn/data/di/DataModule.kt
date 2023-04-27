package com.vmn.data.di

import com.vmn.data.MovieRepoImpl
import com.vmn.data.network.MovieService
import com.vmn.data.utils.ApiManager
import com.vmn.data.utils.NetworkManager
import com.vmn.domain.repo.MovieRepo
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {

    //stateless and can be shared across the app
    single { ApiManager.retrofitInstance(MovieService::class.java) }
    single { NetworkManager(androidContext()) }
    //has a state and not planning to share across the app
    factory { MovieRepoImpl(get(), get()) } bind MovieRepo::class
}