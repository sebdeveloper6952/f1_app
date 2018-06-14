package com.projects.sebdeveloper6952.f1_app

import com.projects.sebdeveloper6952.f1_app.models.Season
import com.projects.sebdeveloper6952.f1_app.models.SeasonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/* TODO implement local database
 */
object F1DataManager {

    private val f1Api = F1ApiSingleton.newInstance()

    fun getSeason(listener: SeasonListener, seasonYear: String) {
        val call = f1Api.getSeason(seasonYear)
        call.enqueue(object: Callback<SeasonResponse> {
            override fun onResponse(call: Call<SeasonResponse>?,
                                    response: Response<SeasonResponse>?) {
                val season = response?.body()?.MRData?.RaceTable!!
                listener.onSeasonUpdated(season)
            }
            override fun onFailure(call: Call<SeasonResponse>?, t: Throwable?) {
                listener.onError("Error fetching season.")
            }
        })
    }

    interface SeasonListener {
        fun onError(message: String)
        fun onSeasonUpdated(season: Season)
    }
}