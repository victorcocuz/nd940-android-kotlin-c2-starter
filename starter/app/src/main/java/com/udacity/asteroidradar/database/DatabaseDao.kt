package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DatabaseDao {

    @Insert
    fun insert(databaseAsteroid: DatabaseAsteroid)

    @Update
    fun update(databaseAsteroid: DatabaseAsteroid)

    @Query("SELECT * FROM asteroid_table WHERE asteroidId = :key")
    fun get(key: Long): DatabaseAsteroid

    @Query("SELECT * FROM asteroid_table ORDER BY asteroidId DESC")
    fun getAllAsteroids(): LiveData<List<DatabaseAsteroid>>

    @Query("SELECT * FROM  asteroid_table ORDER BY asteroidId DESC LIMIT 1")
    fun getLatestAsteroid(): DatabaseAsteroid?

    @Query("DELETE FROM asteroid_table")
    fun clear()
}