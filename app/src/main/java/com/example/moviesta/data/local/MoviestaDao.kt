package com.example.moviesta.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesta.domain.model.Movie
import com.example.moviesta.util.Constant.SELECT_MOVIES_QUERY
import com.example.moviesta.util.Constant.SELECT_MOVIE_DETAILS_QUERY
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviestaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query(SELECT_MOVIES_QUERY)
    fun getMoviesBookmarked(): Flow<List<Movie>>

    @Query(SELECT_MOVIE_DETAILS_QUERY)
    suspend fun getMovieBookmarkedDetails(id: Int): Movie
}