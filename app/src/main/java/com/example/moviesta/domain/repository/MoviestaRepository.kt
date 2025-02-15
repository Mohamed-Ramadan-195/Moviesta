package com.example.moviesta.domain.repository

import com.example.moviesta.data.remote.dto.details.DetailsResponse
import com.example.moviesta.domain.model.Genre
import com.example.moviesta.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviestaRepository {
    // Remote Data Functions
    suspend fun getMovieLists(endPoint: String): Flow<List<Movie>>
    suspend fun getMovieDetails(movieId: Int): Flow<DetailsResponse>
    suspend fun searchMovie(query: String): Flow<List<Movie>>
    suspend fun getGenresList(): Flow<List<Genre>>
    suspend fun getMoviesByGenre(genreId: Int): Flow<List<Movie>>

    // Local Data Functions
    suspend fun insertMovie(movie: Movie)
    suspend fun deleteMovie(movie: Movie)
    fun getMoviesBookmarked(): Flow<List<Movie>>
    suspend fun getMovieBookmarkedDetails(id: Int): Movie?
}