package com.udacity.asteroidradar.network

import com.squareup.moshi.Json

data class PictureOfTheDay(
    @Json(name = "media_type") val mediaType: String,
    val title: String,
    val url: String)