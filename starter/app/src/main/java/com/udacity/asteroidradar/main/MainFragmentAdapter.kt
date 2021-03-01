package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.ListItemAsteroidBinding
import com.udacity.asteroidradar.domain.Asteroid

class MainFragmentAdapter :
    ListAdapter<Asteroid, MainFragmentAdapter.ViewHolder>(AsteroidDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(private val binding: ListItemAsteroidBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Asteroid) {
//            binding.asteroidItemName.text = item.codeName
//            binding.asteroidItemDate.text = item.closeApproachDate
//            binding.asteroidItemHazardIcon.setImageResource(
//                when (item.isPotentiallyHazardous) {
//                    true -> R.drawable.ic_status_potentially_hazardous
//                    false -> R.drawable.ic_status_normal
//                }
//            )
            binding.asteroid = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = ListItemAsteroidBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class AsteroidDiffCallback : DiffUtil.ItemCallback<Asteroid>() {
    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem.asteroidId == newItem.asteroidId
    }

    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem == newItem
    }
}