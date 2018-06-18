package com.projects.sebdeveloper6952.f1_app.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projects.sebdeveloper6952.f1_app.R
import com.projects.sebdeveloper6952.f1_app.models.SeasonStandingsResponse
import kotlinx.android.synthetic.main.item_driver_cardview.view.*

class DriverRecyclerViewAdapter(private val data: List<SeasonStandingsResponse.DriverResult>):
        RecyclerView.Adapter<DriverRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_driver_cardview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(data[position].Driver) {
            val fullName = "$givenName $familyName"
            with(holder) {
                // TODO ("bind driver image to image view")
                txtViewName.text = fullName
            }
        }
    }


    inner class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val imgViewDriver = v.imgView_driver
        val txtViewName = v.txtView_driverName
    }
}