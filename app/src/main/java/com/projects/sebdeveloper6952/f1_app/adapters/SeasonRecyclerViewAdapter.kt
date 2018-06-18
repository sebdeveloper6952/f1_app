package com.projects.sebdeveloper6952.f1_app.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.projects.sebdeveloper6952.f1_app.components.AllSeasonFragment.OnListFragmentInteractionListener
import com.projects.sebdeveloper6952.f1_app.R
import com.projects.sebdeveloper6952.f1_app.models.SeasonScheduleResponse
import kotlinx.android.synthetic.main.item_season_race_cardview.view.*

class SeasonRecyclerViewAdapter(private val data: List<SeasonScheduleResponse.SeasonSchedule>,
                                private val listener: OnListFragmentInteractionListener?):
        RecyclerView.Adapter<SeasonRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        // let know the listener when an item is clicked
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as SeasonScheduleResponse.SeasonSchedule
            listener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_season_race_cardview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.txtViewTitle.text = item.season
        // TODO(bind standing text views with season data)
        with(holder.v) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(val v: View) : RecyclerView.ViewHolder(v) {

        val txtViewTitle = v.txtView_title
        val txtViewFirst = v.txtView_firstPlace
        val txtViewSecond = v.txtView_secondPlace
        val txtViewThird = v.txtView_thirdPlace

        override fun toString(): String {
            return super.toString() + " '" + txtViewTitle.text + "'"
        }
    }
}
