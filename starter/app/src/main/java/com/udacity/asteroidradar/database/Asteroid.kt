package com.udacity.asteroidradar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asteroid_table")
data class Asteroid (
    @PrimaryKey
    val asteroidId: Long = 0L,

    @ColumnInfo(name = "code_name")
    val codeName: String = "",

    @ColumnInfo(name = "close_approaches")
    val closeApproachDate: String = "",

    @ColumnInfo(name = "absolute_magnitude")
    val absoluteMagnitude: Double = -1.0,

    @ColumnInfo(name = "estimated_diameter")
    val estimatedDiameter: Double = -1.0,

    @ColumnInfo(name = "relative_velocity")
    val relativeVelocity: Double = -1.0,

    @ColumnInfo(name = "distance_from_earth")
    val distanceFromEarth: Double = -1.0,

    @ColumnInfo(name = "is_potentially_hazardous")
    val isPotentiallyHazardous: Boolean = false)