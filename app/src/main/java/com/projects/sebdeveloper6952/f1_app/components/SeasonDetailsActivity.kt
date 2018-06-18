package com.projects.sebdeveloper6952.f1_app.components

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.projects.sebdeveloper6952.f1_app.F1DataManager
import com.projects.sebdeveloper6952.f1_app.R
import com.projects.sebdeveloper6952.f1_app.models.SeasonScheduleResponse
import com.projects.sebdeveloper6952.f1_app.models.SeasonStandingsResponse
import kotlinx.android.synthetic.main.activity_season_details.*
import org.jetbrains.anko.toast
import org.jetbrains.anko.startActivity

class SeasonDetailsActivity : AppCompatActivity(),
        F1DataManager.SeasonScheduleListener,
        F1DataManager.SeasonStandingsListener,
        RaceListFragment.OnFragmentInteractionListener {

    // season associated with this activity
    private lateinit var season: SeasonScheduleResponse.SeasonSchedule

    // used for rxjava
    //private var disposable: Disposable? = null

    private val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.nav_races -> {
                        supportActionBar?.title = "Races"
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.nav_driver_standings -> {
                        supportActionBar?.title = "Driver Standings"
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_season_details)
        // item click listener for bottom navigation
        season_details_navigation
                .setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        // get season extra to show its details
        season = intent.getSerializableExtra("season") as SeasonScheduleResponse.SeasonSchedule
        supportActionBar?.title = "${season.season} SeasonSchedule"
        // TODO decide on Retrofit Call or RxJava implementation
        // retrofit way
        F1DataManager.getSeasonSchedule(this, season.season)
        F1DataManager.getSeasonStandings(this, season.season)

        // rxjava way
//        val service = F1ApiSingleton.newRxInstance()
//        disposable = service.getSeason2(season.season)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(this::success, this::error)
    }

//    private fun success(season: SeasonScheduleResponse) {
//        supportFragmentManager.beginTransaction()
//                .add(R.id.fragment_space,
//                        RaceListFragment.newInstance(season.MRData.RaceTable),
//                        "raceList")
//                .commit()
//    }
//
//    private fun error(t: Throwable) {
//        toast("Error fetching data.")
//    }

    override fun onSeasonScheduleUpdated(season: SeasonScheduleResponse.SeasonSchedule) {
        // attach race list fragment
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_space, RaceListFragment.newInstance(season), "raceList")
                .commit()
    }

    override fun onSeasonStandingsUpdated(standings: List<SeasonStandingsResponse.DriverResult>) {
        toast("Got season standings.")
    }

    /**
     * Called if an error occurred fetching data from api.
     */
    override fun onError(message: String) {
        toast(message)
    }

    /**
     * Responds to recycler view item click.
     */
    override fun onFragmentInteraction(race: SeasonScheduleResponse.Race) {
        // show race standings activity
        startActivity<RaceDetailsActivity>("race" to race)
    }
}
