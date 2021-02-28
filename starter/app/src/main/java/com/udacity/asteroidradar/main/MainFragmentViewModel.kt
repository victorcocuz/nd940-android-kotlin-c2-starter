package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.database.AsteroidRadarDatabase
import com.udacity.asteroidradar.database.AsteroidRadarDatabaseDao
import com.udacity.asteroidradar.domain.PictureOfTheDay
import com.udacity.asteroidradar.network.*
import com.udacity.asteroidradar.repository.AsteroidRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import androidx.lifecycle.LiveData as LiveData1

const val APY_KEY = "qYBDhnbQLZsDAbjkUMNSHiw0ftDq57Evbp7PeLTH"

class MainFragmentViewModel(val radarDatabase: AsteroidRadarDatabaseDao, application: Application) :
    AndroidViewModel(application) {
    // Store data from asteroid API, picture API & response status
    private val _asteroidStatus = MutableLiveData<String>()
    val asteroidStatus: LiveData1<String>
        get() = _pictureStatus
    private val _pictureStatus = MutableLiveData<String>()
    val pictureStatus: LiveData1<String>
        get() = _pictureStatus
//    private val _asteroids = MutableLiveData<List<NetworkAsteroid>>()
//    val asteroids: LiveData<List<NetworkAsteroid>>
//        get() = _asteroids
//    private val _pictureOfTheDay = MutableLiveData<NetworkPictureOfTheDay>()
//    val pictureOfTheDay: LiveData<PictureOfTheDay>
//        get() = _pictureOfTheDay

    private val database = AsteroidRadarDatabase.getDatabase(application)
    private val asteroidRepository = AsteroidRepository(database)

    init {
//        getAsteroidsFromApi()
//        getPictureOfTheDayFromApi()
        viewModelScope.launch {
            asteroidRepository.refreshAsteroids()
            asteroidRepository.refreshPictureOfTheDay()
        }
    }

    val asteroids = asteroidRepository.asteroids
    val pictureOfTheDay = asteroidRepository.pictureOfTheDay

//    private fun getAsteroidsFromApi() {
//        viewModelScope.launch {
//            try {
//                val asteroidsList = parseAsteroidsJsonResult(
//                    JSONObject(
//                        AsteroidApi.retrofitService.getAsteroids(
//                            APY_KEY,
//                            getNextSevenDaysFormattedDates()[0],
//                            getNextSevenDaysFormattedDates()[7]
//                        )
//                    )
//                )
//                _asteroidStatus.value = "Asteroids success: ${asteroidsList.size}"
//                if (asteroidsList.size > 0) {
//                    _asteroids.value = asteroidsList
//                }
//            } catch (e: Exception) {
//                _asteroidStatus.value = "Asteroids failure: ${e.message}"
//            }
//        }
//    }

//    private fun getPictureOfTheDayFromApi() {
//        viewModelScope.launch {
//            try {
//                _pictureOfTheDay.value = PictureApi.retrofitService.getNetworkPictureOfTheDay(APY_KEY)
//                _pictureStatus.value = "Picture success: ${_pictureOfTheDay.value}"
//            } catch (e: Exception) {
//                _pictureStatus.value = "Picture failure: ${e.message}"
//            }
//        }
//    }
}