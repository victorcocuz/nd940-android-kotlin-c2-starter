package com.udacity.asteroidradar

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Asteroid(val asteroidId: Long,
                    val codeName: String,
                    val closeApproachDate: String,
                    val absoluteMagnitude: Double,
                    val estimatedDiameter: Double,
                    val relativeVelocity: Double,
                    val distanceFromEarth: Double,
                    val isPotentiallyHazardous: Boolean) : Parcelable