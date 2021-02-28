package com.udacity.asteroidradar.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.udacity.asteroidradar.database.DatabaseAsteroid
import com.udacity.asteroidradar.database.DatabasePictureOfTheDay

@JsonClass(generateAdapter = true)
data class NetworkAsteroidContainer(val asteroids: List<NetworkAsteroid>)

@JsonClass(generateAdapter = true)
data class NetworkAsteroid(
    val asteroidId: Long,
    val codeName: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
)

// Convert NetworkAsteroid -> DatabaseAsteroid
fun NetworkAsteroidContainer.asDatabaseModel(): Array<DatabaseAsteroid> {
    return asteroids.map {
        DatabaseAsteroid(
            asteroidId = it.asteroidId,
            codeName = it.codeName,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous)
    }.toTypedArray()
}

@JsonClass(generateAdapter = true)
data class NetworkPictureOfTheDayContainer(val pictureOfTheDay: NetworkPictureOfTheDay)

@JsonClass(generateAdapter = true)
data class NetworkPictureOfTheDay(
    @Json(name = "media_type") val mediaType: String,
    val title: String,
    val url: String)

//Convert NetworkPictureOfTheDay -> DatabasePictureOfTheDay
fun NetworkPictureOfTheDayContainer.asDatabaseModel(): DatabasePictureOfTheDay {
    return DatabasePictureOfTheDay(
        mediaType = pictureOfTheDay.mediaType,
        title = pictureOfTheDay.title,
        url = pictureOfTheDay.url
    )
}