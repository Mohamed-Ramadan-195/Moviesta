package com.example.moviesta.data.repository

import com.example.moviesta.data.local.MoviestaDao
import com.example.moviesta.data.remote.api.MoviestaApi
import com.example.moviesta.data.remote.dto.details.DetailsResponse
import com.example.moviesta.domain.model.Genre
import com.example.moviesta.domain.model.Movie
import com.example.moviesta.domain.repository.MoviestaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviestaRepositoryImpl @Inject constructor (
    private val moviestaDao: MoviestaDao,
    private val moviestaApi: MoviestaApi
) : MoviestaRepository {
    // Override Remote Data Functions

    override suspend fun getMovieLists(endPoint: String): Flow<List<Movie>> {
        return flow {
            val response = moviestaApi.getMovieLists(endPoint)
            emit(response.movies)
        }.catch {
            println("Error in flow")
            emit(emptyList())
        }
    }

    override suspend fun getMovieDetails(movieId: Int): Flow<DetailsResponse> {
        return flow {
            val response = moviestaApi.getMovieDetails(movieId)
            emit(response)
        }.catch {
            println("Error in flow")
        }
    }

    override suspend fun searchMovie(query: String): Flow<List<Movie>> {
        return flow {
            val response = moviestaApi.searchMovie(query = query)
            emit(response.movies)
        }.catch {
            println("Error in flow")
            emit(emptyList())
        }
    }

    override suspend fun getGenresList(): Flow<List<Genre>> {
        return flow {
            val response = moviestaApi.getGenresList()
            emit(response.genres)
        }.catch {
            println("Error in flow")
            emit(emptyList())
        }
    }

    override suspend fun getMoviesByGenre(genreId: Int): Flow<List<Movie>> {
        return flow {
            val response = moviestaApi.getMoviesByGenre(genreId = genreId)
            emit(response.movies)
        }.catch {
            println("Error in flow")
            emit(emptyList())
        }
    }

    // ------------------------------------------------- //

    // Override Local Data Functions

    override suspend fun insertMovie(movie: Movie) {
        moviestaDao.insertMovie(movie)
    }

    override suspend fun deleteMovie(movie: Movie) {
        moviestaDao.deleteMovie(movie)
    }

    override fun getMoviesBookmarked(): Flow<List<Movie>> {
        return moviestaDao.getMoviesBookmarked()
    }

    override suspend fun getMovieBookmarkedDetails(id: Int): Movie {
        return moviestaDao.getMovieBookmarkedDetails(id = id)
    }
}