package com.projects.sebdeveloper6952.f1_app

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.projects.sebdeveloper6952.f1_app.models.Race
import com.projects.sebdeveloper6952.f1_app.models.Season
import kotlinx.android.synthetic.main.activity_season_details.*
import org.jetbrains.anko.toast

class SeasonDetailsActivity : AppCompatActivity(),
        F1DataManager.SeasonListener,
        RaceListFragment.OnFragmentInteractionListener {

    // season associated with this activity
    private lateinit var season: Season

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

        season = intent.getSerializableExtra("season") as Season
        txtView_seasonTitle.text = season.season

        // item click listener for bottom navigation
        season_details_navigation
                .setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // fetch desired season from data manager
        F1DataManager.getSeason(this, season.season)
    }

    override fun onSeasonUpdated(season: Season) {
        // attach race list fragment
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_space, RaceListFragment.newInstance(season), "raceList")
                .commit()
        toast("onResponse")
    }

    override fun onError(message: String) {
        toast(message)
    }

    override fun onFragmentInteraction(race: Race) {
        toast("Clicked: ${race.raceName}")
    }
}
