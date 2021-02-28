package com.udacity.asteroidradar.domain

data class Asteroid(
    val asteroidId: Long,
    val codeName: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
)

data class PictureOfTheDay(
    val mediaType: String,
    val title: String,
    val url: String
)