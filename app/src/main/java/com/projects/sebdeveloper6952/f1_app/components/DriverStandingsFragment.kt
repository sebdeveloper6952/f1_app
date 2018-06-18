package com.projects.sebdeveloper6952.f1_app.components


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.projects.sebdeveloper6952.f1_app.R
import com.projects.sebdeveloper6952.f1_app.adapters.DriverRecyclerViewAdapter
import com.projects.sebdeveloper6952.f1_app.models.SeasonStandingsResponse
import kotlinx.android.synthetic.main.fragment_driver_standings.view.*


class DriverStandingsFragment : Fragment() {

    private lateinit var standings: ArrayList<SeasonStandingsResponse.DriverResult>
    private val EXTRA_STANDINGS = "standings"

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        standings = ArrayList(arguments?.getParcelableArrayList(EXTRA_STANDINGS))
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_driver_standings, container, false)
        with(layout.recyclerView) {
            layoutManager = LinearLayoutManager(activity)
            adapter = DriverRecyclerViewAdapter(standings)
        }
        return layout
    }

    companion object {
        fun newInstance(standings: List<SeasonStandingsResponse.DriverResult>):
                DriverStandingsFragment {
            val fragment = DriverStandingsFragment()
            with(fragment) {
                val bundle = Bundle()
                bundle.putParcelableArrayList(EXTRA_STANDINGS, ArrayList(standings))
                arguments = bundle
            }
            return fragment
        }
    }
}
