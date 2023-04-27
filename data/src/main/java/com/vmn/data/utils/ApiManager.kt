package com.vmn.data.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {

    private const val BASE_URL = "https://api.themoviedb.org"

    fun <T> retrofitInstance(service: Class<out T>): T = instance.create(service)

    private val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}