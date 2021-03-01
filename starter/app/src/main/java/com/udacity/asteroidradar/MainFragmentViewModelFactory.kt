package com.udacity.asteroidradar

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.asteroidradar.detail.DetailFragmentViewModel
import com.udacity.asteroidradar.main.MainFragmentViewModel

class MainFragmentViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainFragmentViewModel::class.java)) {
            return MainFragmentViewModel(application) as T
        }
        if (modelClass.isAssignableFrom(DetailFragmentViewModel::class.java)) {
            return DetailFragmentViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}