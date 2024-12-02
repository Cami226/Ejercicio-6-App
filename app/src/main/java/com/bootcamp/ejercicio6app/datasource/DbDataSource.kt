package com.bootcamp.ejercicio6app.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bootcamp.ejercicio6app.model.Movie
import com.bootcamp.ejercicio6app.model.MovieDao
@Database(entities = [Movie::class], version = 1)
abstract class DbDataSource: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}