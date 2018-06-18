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

    // extras
    val EXTRA_RACE = "race"

    // schedule associated with this activity
    private lateinit var schedule: SeasonScheduleResponse.SeasonSchedule
    private lateinit var standings: List<SeasonStandingsResponse.DriverResult>

    private var racesFragment: RaceListFragment? = null
    private var standingsFragment: DriverStandingsFragment? = null

    // bottom navigation item selected listener
    private val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.nav_races -> {
                        if(racesFragment != null) {
                            supportFragmentManager.beginTransaction()
                                    .replace(R.id.fragment_space, racesFragment)
                                    .commit()
                            supportActionBar?.title = "Races"
                            return@OnNavigationItemSelectedListener true
                        }
                    }
                    R.id.nav_driver_standings -> {
                        if(standingsFragment != null) {
                            supportFragmentManager.beginTransaction()
                                    .replace(R.id.fragment_space, standingsFragment)
                                    .commit()
                            supportActionBar?.title = "Driver Standings"
                            return@OnNavigationItemSelectedListener true
                        }
                    }
                }
                false
            }

    // used for rxjava
    //private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_season_details)
        // item click listener for bottom navigation
        season_details_navigation
                .setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        // get schedule extra to show its details
        schedule = intent
                .getSerializableExtra(HomeActivity.EXTRA_SEASON)
                as SeasonScheduleResponse.SeasonSchedule

        // set support action bar title to match selected season year
        supportActionBar?.title = "${schedule.season} Season"
        // TODO decide on Retrofit Call or RxJava implementation
        // retrofit way
        F1DataManager.getSeasonSchedule(this, schedule.season)
        F1DataManager.getSeasonStandings(this, schedule.season)

        // rxjava way
//        val service = F1ApiSingleton.newRxInstance()
//        disposable = service.getSeason2(schedule.schedule)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(this::success, this::error)
    }

//    private fun success(schedule: SeasonScheduleResponse) {
//        supportFragmentManager.beginTransaction()
//                .add(R.id.fragment_space,
//                        RaceListFragment.newInstance(schedule.MRData.RaceTable),
//                        "raceList")
//                .commit()
//    }
//
//    private fun error(t: Throwable) {
//        toast("Error fetching data.")
//    }

    override fun onSeasonScheduleUpdated(season: SeasonScheduleResponse.SeasonSchedule) {
        // attach race list fragment
        if(racesFragment == null) racesFragment = RaceListFragment.newInstance(season)
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_space, racesFragment)
                .commit()
//        supportFragmentManager.beginTransaction()
//                .add(R.id.fragment_space, RaceListFragment.newInstance(season), "raceList")
//                .commit()
    }

    override fun onSeasonStandingsUpdated(standings: List<SeasonStandingsResponse.DriverResult>) {
        this.standings = standings
        if(standingsFragment == null) standingsFragment =
                DriverStandingsFragment.newInstance(standings)
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
        startActivity<RaceDetailsActivity>(EXTRA_RACE to race)
    }
}
