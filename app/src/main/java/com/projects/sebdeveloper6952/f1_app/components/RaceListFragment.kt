package com.projects.sebdeveloper6952.f1_app

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projects.sebdeveloper6952.f1_app.adapters.RaceRecyclerViewAdapter
import com.projects.sebdeveloper6952.f1_app.models.Race
import com.projects.sebdeveloper6952.f1_app.models.Season
import kotlinx.android.synthetic.main.fragment_race_list.view.*

class RaceListFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var season: Season

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        season = arguments?.get("season") as Season
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_race_list, container, false)
        with(layout.recyclerView) {
            layoutManager = LinearLayoutManager(activity)
            adapter = RaceRecyclerViewAdapter(season.Races, listener)
        }
        return layout
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(race: Race)
    }

    companion object {
        fun newInstance(season: Season): RaceListFragment {
            val fragment = RaceListFragment().apply {
                val bundle = Bundle()
                bundle.putSerializable("season", season)
                arguments = bundle
            }
            return fragment
        }
    }
}
