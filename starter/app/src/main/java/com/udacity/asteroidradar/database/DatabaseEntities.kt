package com.udacity.asteroidradar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.PictureOfTheDay

@Entity(tableName = "asteroid_table")
data class DatabaseAsteroid (
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

@Entity(tableName = "picture_of_the_day_table")
data class DatabasePictureOfTheDay (
    @PrimaryKey(autoGenerate = true)
    val pictureId: Long = 0L,

    @ColumnInfo(name = "media_type")
    val mediaType: String ="",

    @ColumnInfo(name = "title")
    val title: String = "",

    @ColumnInfo(name = "url")
    val url: String = "")

//  Convert DatabaseAsteroid -> Domain Asteroid
fun List<DatabaseAsteroid>.asDomainAsteroids(): List<Asteroid> {
    return map {
        Asteroid (
            asteroidId = it.asteroidId,
            codeName = it.codeName,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous)
    }
}

fun DatabaseAsteroid.asDomainAsteroid(): Asteroid {
    return Asteroid (
        asteroidId = this.asteroidId,
        codeName = this.codeName,
        closeApproachDate = this.closeApproachDate,
        absoluteMagnitude = this.absoluteMagnitude,
        estimatedDiameter = this.estimatedDiameter,
        relativeVelocity = this.relativeVelocity,
        distanceFromEarth = this.distanceFromEarth,
        isPotentiallyHazardous = this.isPotentiallyHazardous)
}

fun DatabasePictureOfTheDay.asDomainPicture(): PictureOfTheDay {
    return PictureOfTheDay(
            mediaType = this.mediaType,
            title = this.title,
            url = this.url
        )
}