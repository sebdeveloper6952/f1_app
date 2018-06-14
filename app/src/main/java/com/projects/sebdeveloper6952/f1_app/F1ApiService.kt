package com.projects.sebdeveloper6952.f1_app

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface F1ApiService {

    @GET("{season}.json")
    fun getSeason(@Path("season") season: String?): Call<SeasonResponse>
}