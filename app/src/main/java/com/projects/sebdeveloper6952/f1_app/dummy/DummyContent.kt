package com.projects.sebdeveloper6952.f1_app.dummy

//import com.projects.sebdeveloper6952.f1_app.models.Season
import com.projects.sebdeveloper6952.f1_app.models.SeasonScheduleResponse
import java.util.ArrayList

object DummyContent {

    val SEASONS: MutableList<SeasonScheduleResponse.SeasonSchedule> = ArrayList()

    private var currentYear = 2018

    init {
        // Add some sample items.
        for (i in 1950..currentYear) {
            addSeason(createDummySeason(i.toString()))
        }
    }

    private fun addSeason(season: SeasonScheduleResponse.SeasonSchedule) {
        SEASONS.add(season)
    }

    private fun createDummySeason(year: String): SeasonScheduleResponse.SeasonSchedule {
        return SeasonScheduleResponse.SeasonSchedule(year, arrayListOf())
    }
}
