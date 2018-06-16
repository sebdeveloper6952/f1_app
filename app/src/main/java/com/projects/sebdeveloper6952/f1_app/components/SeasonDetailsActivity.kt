package com.projects.sebdeveloper6952.f1_app.components

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.projects.sebdeveloper6952.f1_app.F1ApiSingleton
import com.projects.sebdeveloper6952.f1_app.F1DataManager
import com.projects.sebdeveloper6952.f1_app.R
import com.projects.sebdeveloper6952.f1_app.models.Race
import com.projects.sebdeveloper6952.f1_app.models.Season
import com.projects.sebdeveloper6952.f1_app.models.SeasonResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_season_details.*
import org.jetbrains.anko.toast

class SeasonDetailsActivity : AppCompatActivity(),
        F1DataManager.DataListener,
        RaceListFragment.OnFragmentInteractionListener {

    // season associated with this activity
    private lateinit var season: Season

    private var disposable: Disposable? = null

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


        // TODO decide on Retrofit Call or RxJava implementation
        // retrofit way
        F1DataManager.getSeason(this, season.season)

        // rxjava way
//        val service = F1ApiSingleton.newRxInstance()
//        disposable = service.getSeason2(season.season)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(this::success, this::error)
    }

//    private fun success(season: SeasonResponse) {
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

    /**
     * Called when season data is ready.
     */
    override fun onSeasonUpdated(season: Season) {
        // attach race list fragment
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_space, RaceListFragment.newInstance(season), "raceList")
                .commit()
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
    override fun onFragmentInteraction(race: Race) {
        // show race standings activity
        toast("Clicked: ${race.raceName}")
    }
}
