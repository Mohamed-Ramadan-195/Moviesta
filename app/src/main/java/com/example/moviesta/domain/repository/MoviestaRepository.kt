package com.example.moviesta.domain.repository

import com.example.moviesta.data.remote.dto.details.DetailsResponse
import com.example.moviesta.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface MoviestaRepository {
    suspend fun getMovieLists(endPoint: String): Flow<List<Movies>>
    suspend fun getMovieDetails(movieId: Int): Flow<DetailsResponse>
}