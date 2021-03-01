package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.domain.Asteroid

//class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)

class MainFragmentAdapter: RecyclerView.Adapter<MainFragmentAdapter.ViewHolder>() {
    var data = listOf<Asteroid>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_asteroid, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.asteroidName.text = item.codeName
        holder.asteroidDate.text = item.closeApproachDate
        holder.asteroidHazardIcon.setImageResource(when (item.isPotentiallyHazardous) {
            true -> R.drawable.ic_status_potentially_hazardous
            false -> R.drawable.ic_status_normal
        })
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val asteroidName: TextView = itemView.findViewById(R.id.asteroid_item_name)
        val asteroidDate: TextView = itemView.findViewById(R.id.asteroid_item_date)
        val asteroidHazardIcon: ImageView = itemView.findViewById(R.id.asteroid_item_hazard_icon)
    }
}