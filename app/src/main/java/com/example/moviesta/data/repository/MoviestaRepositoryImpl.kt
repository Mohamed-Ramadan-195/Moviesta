package com.example.moviesta.data.repository

import com.example.moviesta.data.remote.api.MoviestaApi
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
            emit(response.results)
        }.catch {
            println("Error in flow")
            emit(emptyList())
        }
    }
}