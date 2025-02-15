package com.example.moviesta.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviesta.domain.model.Movie

@Database(entities = [Movie::class], version = 1)
@TypeConverters(MoviestaTypeConverter::class)
abstract class MoviestaDatabase : RoomDatabase() {
    abstract val moviestaDao: MoviestaDao
}