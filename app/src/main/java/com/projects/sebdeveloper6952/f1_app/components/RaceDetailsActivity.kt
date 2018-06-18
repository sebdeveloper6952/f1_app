package com.projects.sebdeveloper6952.f1_app.components

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.projects.sebdeveloper6952.f1_app.F1DataManager
import com.projects.sebdeveloper6952.f1_app.R
import com.projects.sebdeveloper6952.f1_app.models.SeasonScheduleResponse
import com.projects.sebdeveloper6952.f1_app.models.SeasonStandingsResponse
import org.jetbrains.anko.toast

class RaceDetailsActivity : AppCompatActivity() {

    private var race: SeasonScheduleResponse.Race? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_race_details)
        race = intent.getSerializableExtra("race") as SeasonScheduleResponse.Race
        supportActionBar?.title = race?.raceName
    }
}
