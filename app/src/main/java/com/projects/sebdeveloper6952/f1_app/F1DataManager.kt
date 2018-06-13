package com.projects.sebdeveloper6952.f1_app

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: implement Room local database
class F1DataManager {

    companion object {

        private val service = F1ApiService.newInstance()

        fun getSeason(listener: SeasonListener, seasonYear: String) {
            val call = service.getSeason(seasonYear)
            call.enqueue(object: Callback<Season> {
                override fun onResponse(call: Call<Season>?, response: Response<Season>?) {
                    listener.onSeasonUpdated(response?.body()!!)
                }
                override fun onFailure(call: Call<Season>?, t: Throwable?) {
                    listener.onError("Error fetching season.")
                }
            })
        }
    }

    interface SeasonListener {
        fun onSeasonUpdated(season: Season)
        fun onError(message: String)
    }
}