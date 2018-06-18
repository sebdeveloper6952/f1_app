package com.projects.sebdeveloper6952.f1_app

//import com.projects.sebdeveloper6952.f1_app.models.Season
import com.projects.sebdeveloper6952.f1_app.models.SeasonScheduleResponse
import com.projects.sebdeveloper6952.f1_app.models.SeasonStandingsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/* TODO implement local database
 */
object F1DataManager {

    private val f1Api = F1ApiSingleton.newInstance()

    fun getSeasonSchedule(listener: SeasonScheduleListener, seasonYear: String) {
        val call = f1Api.getSeason(seasonYear)
        call.enqueue(object: Callback<SeasonScheduleResponse> {
            override fun onResponse(call: Call<SeasonScheduleResponse>?,
                                    response: Response<SeasonScheduleResponse>?) {
                val season = response?.body()?.MRData?.RaceTable!!
                listener.onSeasonScheduleUpdated(season)
            }
            override fun onFailure(call: Call<SeasonScheduleResponse>?, t: Throwable?) {
                listener.onError("Error fetching season schedule.")
            }
        })
    }

    fun getSeasonStandings(listener: SeasonStandingsListener, seasonYear: String) {
        val call = f1Api.getSeasonStandings(seasonYear)
        call.enqueue(object: Callback<SeasonStandingsResponse> {
            override fun onResponse(call: Call<SeasonStandingsResponse>?,
                                    response: Response<SeasonStandingsResponse>?) {
                val standings = response?.body()?.MRData
                        ?.StandingsTable
                        ?.StandingsLists
                        ?.get(0)?.DriverStandings!!
                listener.onSeasonStandingsUpdated(standings)
            }
            override fun onFailure(call: Call<SeasonStandingsResponse>?, t: Throwable?) {
                listener.onError("Error fetching season standings.")
            }
        })
    }

    interface SeasonScheduleListener: ErrorListener {
        fun onSeasonScheduleUpdated(season: SeasonScheduleResponse.SeasonSchedule)
    }

    interface SeasonStandingsListener: ErrorListener {
        fun onSeasonStandingsUpdated(standings: List<SeasonStandingsResponse.DriverResult>)
    }

    interface ErrorListener {
        fun onError(message: String)
    }
}