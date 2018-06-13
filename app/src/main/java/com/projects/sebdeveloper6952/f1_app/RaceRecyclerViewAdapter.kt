package com.projects.sebdeveloper6952.f1_app

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import kotlinx.android.synthetic.main.item_race.view.*

class RaceRecyclerViewAdapter(private val data: List<Race>):
        RecyclerView.Adapter<RaceRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_race, parent, false)
        return ViewHolder(layout)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val race = data[position]
        holder.txtViewName.text = race.name
        with(holder.v) {
            tag = race
        }
    }

    inner class ViewHolder(val v: View): RecyclerView.ViewHolder(v) {
        var txtViewName = v.txtView_RaceName
    }
}