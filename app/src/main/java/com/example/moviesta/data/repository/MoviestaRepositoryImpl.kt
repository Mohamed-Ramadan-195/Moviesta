package com.example.moviesta.data.repository

import com.example.moviesta.data.remote.api.MoviestaApi
import com.example.moviesta.data.remote.dto.details.DetailsResponse
import com.example.moviesta.domain.model.Movies
import com.example.moviesta.domain.repository.MoviestaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviestaRepositoryImpl @Inject constructor (
    // private val moviestaDao: MoviestaDao,
    private val moviestaApi: MoviestaApi
) : MoviestaRepository {
    override suspend fun getMovieLists(endPoint: String): Flow<List<Movies>> {
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

    override suspend fun searchMovie(query: String): Flow<List<Movies>> {
        return flow {
            val response = moviestaApi.searchMovie(query = query)
            emit(response.movies)
        }.catch {
            println("Error in flow")
            emit(emptyList())
        }
    }
}