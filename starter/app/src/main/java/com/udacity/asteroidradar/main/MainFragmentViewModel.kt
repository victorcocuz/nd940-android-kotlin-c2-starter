package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.database.AsteroidRadarDatabase
import com.udacity.asteroidradar.database.AsteroidRadarDatabaseDao
import com.udacity.asteroidradar.network.*
import com.udacity.asteroidradar.repository.AsteroidRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import com.udacity.asteroidradar.domain.Asteroid
import timber.log.Timber


class MainFragmentViewModel(application: Application) :
    AndroidViewModel(application) {

    private val asteroidRepository = AsteroidRepository(AsteroidRadarDatabase.getDatabase(application))
    private val _navigateToDetailFragment = MutableLiveData<Long>()
    val navigateToDetailFragment: LiveData<Long>
        get() = _navigateToDetailFragment


    var asteroids = asteroidRepository.getAsteroids(getNextSevenDaysFormattedDates()[0], getNextSevenDaysFormattedDates()[7])

    val pictureOfTheDay = asteroidRepository.pictureOfTheDay

    init {
        viewModelScope.launch {
            asteroidRepository.refreshAsteroids()
            asteroidRepository.refreshPictureOfTheDay()
        }
    }

    fun getAsteroids(dayStart: String, dayEnd: String) {
        asteroids = asteroidRepository.getAsteroids(dayStart, dayEnd)
    }

    fun onAsteroidClicked(asteroidId: Long) {
        _navigateToDetailFragment.value = asteroidId
    }

    fun onShowDetailNavigated() {
        _navigateToDetailFragment.value = null
    }
}