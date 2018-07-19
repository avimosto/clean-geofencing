package com.avimosto.geofencingclient.presentation.features.locationlist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.avimosto.geofencingclient.R

class LocationListAdapter : RecyclerView.Adapter<LocationListAdapter.ViewHolder>() {

    var locations: List<LocationView> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item_location, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val location = locations[position]
        holder.date.text = location.date
        holder.duration.text = location.duration
        holder.location.text = location.location
    }

    override fun getItemCount(): Int = locations.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var date: TextView
        var duration: TextView
        var location: TextView

        init {
            date     = view.findViewById(R.id.tv_date)
            duration = view.findViewById(R.id.tv_duration)
            location = view.findViewById(R.id.tv_location)
        }
    }
}