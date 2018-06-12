package com.projects.sebdeveloper6952.f1_app.dummy

import com.projects.sebdeveloper6952.f1_app.Season
import java.util.ArrayList
import java.util.HashMap

object DummyContent {

    val SEASONS: MutableList<Season> = ArrayList()

    private var currentYear = 2018

    init {
        // Add some sample items.
        for (i in 1950..currentYear) {
            addSeason(createDummySeason(i))
        }
    }

    private fun addSeason(season: Season) {
        SEASONS.add(season)
    }

    private fun createDummySeason(year: Int): Season {
        return Season(year)
    }
}
