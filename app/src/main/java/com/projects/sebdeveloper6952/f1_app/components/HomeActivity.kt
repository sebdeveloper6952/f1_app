package com.projects.sebdeveloper6952.f1_app

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.projects.sebdeveloper6952.f1_app.models.Season
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.startActivity

class HomeActivity : AppCompatActivity(), AllSeasonFragment.OnListFragmentInteractionListener {

    private val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_seasons -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_drivers -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_constructors -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_circuits -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        home_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // add all seasons fragment
        supportFragmentManager
                .beginTransaction()
                .add(R.id.homeAct_rootLayout, AllSeasonFragment.newInstance(), "allSeasons")
                .commit()
    }

    override fun onListFragmentInteraction(season: Season?) {
        startActivity<SeasonDetailsActivity>("season" to season)
    }
}
