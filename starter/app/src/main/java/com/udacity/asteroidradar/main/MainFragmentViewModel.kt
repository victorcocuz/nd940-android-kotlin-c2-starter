package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.database.DatabaseDao
import com.udacity.asteroidradar.network.*
import kotlinx.coroutines.launch
import org.json.JSONObject

const val APY_KEY = "qYBDhnbQLZsDAbjkUMNSHiw0ftDq57Evbp7PeLTH"

class MainFragmentViewModel(val database: DatabaseDao, application: Application) :
    AndroidViewModel(application) {
    // Store data from asteroid API, picture API & response status
    private val _asteroidStatus = MutableLiveData<String>()
    val asteroidStatus: LiveData<String>
        get() = _pictureStatus
    private val _pictureStatus = MutableLiveData<String>()
    val pictureStatus: LiveData<String>
        get() = _pictureStatus
    private val _asteroids = MutableLiveData<List<NetworkAsteroid>>()
    val asteroids: LiveData<List<NetworkAsteroid>>
        get() = _asteroids
    private val _pictureOfTheDay = MutableLiveData<PictureOfTheDay>()
    val pictureOfTheDay: LiveData<PictureOfTheDay>
        get() = _pictureOfTheDay

    init {
        getAsteroidsFromApi()
        getPictureOfTheDayFromApi()
    }

    private fun getAsteroidsFromApi() {
        viewModelScope.launch {
            try {
                val asteroidsList = parseAsteroidsJsonResult(
                    JSONObject(
                        AsteroidApi.retrofitService.getAsteroids(
                            APY_KEY,
                            getNextSevenDaysFormattedDates()[0],
                            getNextSevenDaysFormattedDates()[7]
                        )
                    )
                )
                _asteroidStatus.value = "Asteroids success: ${asteroidsList.size}"
                if (asteroidsList.size > 0) {
                    _asteroids.value = asteroidsList
                }
            } catch (e: Exception) {
                _asteroidStatus.value = "Asteroids failure: ${e.message}"
            }
        }
    }

    private fun getPictureOfTheDayFromApi() {
        viewModelScope.launch {
            try {
                _pictureOfTheDay.value = PictureApi.retrofitService.getPictureOfTheDay(APY_KEY)
                _pictureStatus.value = "Picture success: ${_pictureOfTheDay.value}"
            } catch (e: Exception) {
                _pictureStatus.value = "Picture failure: ${e.message}"
            }
        }
    }
}