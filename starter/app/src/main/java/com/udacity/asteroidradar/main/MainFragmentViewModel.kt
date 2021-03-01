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

    private val database = AsteroidRadarDatabase.getDatabase(application)
    private val asteroidRepository = AsteroidRepository(database)

    init {
        viewModelScope.launch {
            asteroidRepository.refreshAsteroids()
            asteroidRepository.refreshPictureOfTheDay()
        }
    }

    val asteroids = asteroidRepository.asteroids
    val pictureOfTheDay = asteroidRepository.pictureOfTheDay
}