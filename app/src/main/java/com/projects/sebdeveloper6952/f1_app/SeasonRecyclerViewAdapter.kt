package com.projects.sebdeveloper6952.f1_app

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.projects.sebdeveloper6952.f1_app.AllSeasonFragment.OnListFragmentInteractionListener
import kotlinx.android.synthetic.main.item_season.view.*

class SeasonRecyclerViewAdapter(private val data: List<Season>,
                                private val listener: OnListFragmentInteractionListener?):
        RecyclerView.Adapter<SeasonRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        // let know the listener when an item is clicked
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Season
            listener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_season, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.txtViewYear.text = item.season
        with(holder.v) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(val v: View) : RecyclerView.ViewHolder(v) {

        val txtViewYear: TextView = v.txtView_seasonYear

        override fun toString(): String {
            return super.toString() + " '" + txtViewYear.text + "'"
        }
    }
}
