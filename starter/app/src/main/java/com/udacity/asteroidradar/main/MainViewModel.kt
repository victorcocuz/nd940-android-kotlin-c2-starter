package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.network.*
import kotlinx.coroutines.launch
import org.json.JSONObject
import timber.log.Timber

const val APY_KEY = "qYBDhnbQLZsDAbjkUMNSHiw0ftDq57Evbp7PeLTH"

class MainViewModel : ViewModel() {
    // Store most recent response
    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status
//    private val _status = MutableLiveData<List<Asteroid>>()
//    val status: LiveData<List<Asteroid>>
//        get() = _status
    // Store picture of the day
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
                val asteroidsList = AsteroidApi.retrofitService.getAsteroids(
                    APY_KEY, getNextSevenDaysFormattedDates()[0], getNextSevenDaysFormattedDates()[7])
                _status.value = "Success: ${parseAsteroidsJsonResult(JSONObject(asteroidsList)).size}"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
            Timber.e("Status: ${_status.value}")
        }
    }

    private fun getPictureOfTheDayFromApi() {
        viewModelScope.launch {
            try {
                _pictureOfTheDay.value = PictureApi.retrofitService.getPictureOfTheDay(APY_KEY)
                Timber.e("Picture: ${_pictureOfTheDay.value}")
            } catch (e: Exception) {
                Timber.e("Exception: ${e.message}")
                _pictureOfTheDay.value = null
            }
        }
    }
}