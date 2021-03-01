package com.udacity.asteroidradar.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

import com.udacity.asteroidradar.database.AsteroidRadarDatabase
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.repository.AsteroidRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val asteroidRepository = AsteroidRepository(AsteroidRadarDatabase.getDatabase(application))

    private val _asteroid = MutableLiveData<Asteroid>()
    val asteroid : LiveData<Asteroid>
        get() = _asteroid

    fun getAsteroidById(id: Long) {
        viewModelScope.launch {
            _asteroid.value = asteroidRepository.getAsteroidById(id)
        }
    }
}