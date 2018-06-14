package com.projects.sebdeveloper6952.f1_app

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_race.view.*

class RaceRecyclerViewAdapter(private val data: List<Race>?,
                              private val listener: RaceListFragment.OnFragmentInteractionListener?):
        RecyclerView.Adapter<RaceRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        // the clicked view has a Race object as its tag
        onClickListener = View.OnClickListener { v ->
            val race = v.tag as Race
            listener?.onFragmentInteraction(race)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_race, parent, false)
        return ViewHolder(layout)
    }

    override fun getItemCount() = data?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val race = data?.get(position)
        holder.txtViewName.text = race?.raceName
        with(holder.v) {
            tag = race
            setOnClickListener(onClickListener)
        }
    }

    inner class ViewHolder(val v: View): RecyclerView.ViewHolder(v) {
        var txtViewName = v.txtView_RaceName
    }
}