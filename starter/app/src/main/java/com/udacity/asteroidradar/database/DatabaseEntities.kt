package com.udacity.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseAsteroid::class], version = 1, exportSchema = false)
abstract class DatabaseEntities : RoomDatabase() {
    abstract val databaseDao: DatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseEntities? = null

        fun getInstance(context: Context): DatabaseEntities {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseEntities::class.java,
                        "asteroid_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return instance
            }
        }
    }
}