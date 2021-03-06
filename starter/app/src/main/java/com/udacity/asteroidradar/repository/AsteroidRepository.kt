package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.database.AsteroidRadarDatabase
import com.udacity.asteroidradar.database.asDomainAsteroid
import com.udacity.asteroidradar.database.asDomainAsteroids
import com.udacity.asteroidradar.database.asDomainPicture
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.PictureOfTheDay
import com.udacity.asteroidradar.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import timber.log.Timber

const val APY_KEY = BuildConfig.API_KEY

//Repo for fetching asteroids from the network and storing them on the disk
class AsteroidRepository(private val database: AsteroidRadarDatabase) {
    //  Refresh videos stored in the offline cache
    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            val asteroids = NetworkAsteroidContainer(
                parseAsteroidsJsonResult(
                    JSONObject(
                        AsteroidApi.retrofitService.getNetworkAsteroids(
                            APY_KEY,
                            getNextSevenDaysFormattedDates()[0],
                            getNextSevenDaysFormattedDates()[7]
                        )
                    )
                )
            )
//            database.asteroidRadarDatabaseDao.clearAsteroids()
            database.asteroidRadarDatabaseDao.insertAsteroids(*asteroids?.asDatabaseModel())
        }
    }

    // Define main Interface for asteroid repository
//    val asteroids: LiveData<List<Asteroid>> =
    fun getAsteroids(dayStart: String, dayEnd: String): LiveData<List<Asteroid>> {
        return Transformations.map(database.asteroidRadarDatabaseDao.getAsteroids(dayStart, dayEnd)) {
            it?.asDomainAsteroids()
        }
    }


    suspend fun getAsteroidById(id: Long): Asteroid {
        return withContext(Dispatchers.IO) {
            database.asteroidRadarDatabaseDao.getAsteroidById(id)?.asDomainAsteroid()
        }
    }

    suspend fun refreshPictureOfTheDay() {
        withContext(Dispatchers.IO) {
            val pictureOfTheDay = NetworkPictureOfTheDayContainer(
                PictureApi.retrofitService.getNetworkPictureOfTheDay(APY_KEY)
            )
//            database.asteroidRadarDatabaseDao.clearPictureOfTheDay()
            database.asteroidRadarDatabaseDao.insertPictureOfTheDay(pictureOfTheDay?.asDatabaseModel())
        }
    }

    val pictureOfTheDay: LiveData<PictureOfTheDay> =
        Transformations.map(database.asteroidRadarDatabaseDao.getPictureOfTheDay()) {
            it?.asDomainPicture()
        }
}