package com.projects.sebdeveloper6952.f1_app

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.projects.sebdeveloper6952.f1_app.dummy.DummyContent
import kotlinx.android.synthetic.main.fragment_all_seasons_list.view.*

class AllSeasonFragment : Fragment() {

    private var listener: OnListFragmentInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_all_seasons_list, container, false)
        with(layout) {
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.adapter = SeasonRecyclerViewAdapter(DummyContent.SEASONS, listener)
        }
        return layout
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(season: Season?)
    }

    companion object {
        fun newInstance() = AllSeasonFragment()
    }
}
