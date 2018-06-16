package com.projects.sebdeveloper6952.f1_app.components

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.projects.sebdeveloper6952.f1_app.R
import com.projects.sebdeveloper6952.f1_app.models.Race

class RaceDetailsActivity : AppCompatActivity() {

    private var race: Race? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_race_details)
        race = intent.getSerializableExtra("race") as Race
        supportActionBar?.title = race?.raceName
    }
}
