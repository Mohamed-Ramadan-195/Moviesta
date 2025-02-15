package com.example.moviesta.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesta.domain.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviestaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM Movie")
    fun getMoviesBookmarked(): Flow<List<Movie>>

    @Query("SELECT * FROM Movie WHERE id=:id")
    suspend fun getMovieBookmarkedDetails(id: Int): Movie
}