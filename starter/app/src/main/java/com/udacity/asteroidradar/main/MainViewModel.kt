package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.network.AsteroidApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

const val APY_KEY = "qYBDhnbQLZsDAbjkUMNSHiw0ftDq57Evbp7PeLTH"

class MainViewModel : ViewModel() {
    // Store most recent response
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        getAsteroidsFromApi()
    }

    private fun getAsteroidsFromApi() {
        AsteroidApi.retrofitService.getAsteroids(APY_KEY).enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response.value = response.body()
                Timber.e("ok: ${response.body()}")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure: ${t.message}"
                Timber.e("whatever")
            }
        })
    }
}