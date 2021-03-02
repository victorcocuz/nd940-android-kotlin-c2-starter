package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AsteroidRadarDatabaseDao {

//    @Insert
//    fun insert(databaseAsteroid: DatabaseAsteroid)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAsteroids(vararg asteroids: DatabaseAsteroid)

//    @Update
//    fun update(databaseAsteroid: DatabaseAsteroid)

    @Query("SELECT * FROM asteroid_table WHERE close_approaches BETWEEN :dayStart AND :dayEnd ORDER BY close_approaches ASC ")
    fun getAsteroids(dayStart: String, dayEnd: String): LiveData<List<DatabaseAsteroid>>

    @Query("SELECT * FROM asteroid_table WHERE asteroidId = :key")
    fun getAsteroidById(key: Long): DatabaseAsteroid

//    @Query("SELECT * FROM  asteroid_table ORDER BY asteroidId DESC LIMIT 1")
//    fun getLatestAsteroid(): DatabaseAsteroid?
//
    @Query("DELETE FROM asteroid_table")
    fun clearAsteroids()

    @Insert
    fun insertPictureOfTheDay(databasePictureOfTheDay: DatabasePictureOfTheDay)

    @Query("SELECT * FROM picture_of_the_day_table LIMIT 1")
    fun getPictureOfTheDay(): LiveData<DatabasePictureOfTheDay>

    @Query("DELETE FROM picture_of_the_day_table")
    fun clearPictureOfTheDay()
}