package com.projects.sebdeveloper6952.f1_app.interfaces

import com.projects.sebdeveloper6952.f1_app.models.SeasonScheduleResponse
import com.projects.sebdeveloper6952.f1_app.models.SeasonStandingsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface F1ApiService {

    @GET("{season}.json")
    fun getSeason(@Path("season") season: String?): Call<SeasonScheduleResponse>

    @GET("{season}/driverStandings.json")
    fun getSeasonStandings(@Path("season") season: String?): Call<SeasonStandingsResponse>

//    @GET("{season}.json")
//    fun getSeason2(@Path("season") season: String): Single<SeasonScheduleResponse>
}