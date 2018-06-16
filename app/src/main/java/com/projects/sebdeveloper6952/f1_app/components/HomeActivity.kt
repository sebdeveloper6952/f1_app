package com.projects.sebdeveloper6952.f1_app.components

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.projects.sebdeveloper6952.f1_app.R
import com.projects.sebdeveloper6952.f1_app.models.Season
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.startActivity

class HomeActivity : AppCompatActivity(), AllSeasonFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

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
