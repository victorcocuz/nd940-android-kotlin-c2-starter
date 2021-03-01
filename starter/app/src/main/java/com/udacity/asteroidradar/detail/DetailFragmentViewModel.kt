package com.udacity.asteroidradar.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.asteroidradar.database.AsteroidRadarDatabase
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.repository.AsteroidRepository

class DetailFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val asteroidRepository = AsteroidRepository(AsteroidRadarDatabase.getDatabase(application))
    fun getAsteroidById(id: Long) : Asteroid? = asteroidRepository.getAsteroidById(id).value
}