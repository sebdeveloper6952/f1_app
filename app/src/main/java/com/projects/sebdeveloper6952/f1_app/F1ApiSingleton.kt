package com.projects.sebdeveloper6952.f1_app

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object F1ApiSingleton {
    private const val baseUrl = "http://ergast.com/api/f1/"

    fun newInstance(): F1ApiService {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(F1ApiService::class.java)
    }
}