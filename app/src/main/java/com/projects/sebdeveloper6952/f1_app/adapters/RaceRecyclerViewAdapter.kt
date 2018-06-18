package com.projects.sebdeveloper6952.f1_app.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projects.sebdeveloper6952.f1_app.R
import com.projects.sebdeveloper6952.f1_app.components.RaceListFragment
import com.projects.sebdeveloper6952.f1_app.models.SeasonScheduleResponse
import kotlinx.android.synthetic.main.item_season_race_cardview.view.*

class RaceRecyclerViewAdapter(private val data: List<SeasonScheduleResponse.Race>?,
                              private val listener: RaceListFragment.OnFragmentInteractionListener?):
        RecyclerView.Adapter<RaceRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        // the clicked view has a Race object as its tag
        onClickListener = View.OnClickListener { v ->
            val race = v.tag as SeasonScheduleResponse.Race
            listener?.onFragmentInteraction(race)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_season_race_cardview, parent, false)
        return ViewHolder(layout)
    }

    override fun getItemCount() = data?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val race = data?.get(position)
        holder.txtViewTitle.text = race?.raceName
        // TODO(bind race standings to text views)
        with(holder.v) {
            tag = race
            setOnClickListener(onClickListener)
        }
    }

    inner class ViewHolder(val v: View): RecyclerView.ViewHolder(v) {
        val txtViewTitle = v.txtView_title
        val txtViewFirst = v.txtView_firstPlace
        val txtViewSecond = v.txtView_secondPlace
        val txtViewThird = v.txtView_thirdPlace
    }
}