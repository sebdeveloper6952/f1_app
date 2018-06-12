package com.projects.sebdeveloper6952.f1_app

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_season_details.*
import java.io.Serializable

class SeasonDetailsActivity : AppCompatActivity() {

    private var season: Season? = null

    private val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.nav_races -> {
                        txtView_seasonTitle.text = "Races"
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.nav_driver_standings -> {
                        txtView_seasonTitle.text = "Driver Standings"
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_season_details)

        // TODO: testing
        season = intent.getSerializableExtra("season") as Season?
        txtView_seasonTitle.text = season?.year.toString()

        season_details_navigation
                .setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
