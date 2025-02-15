package com.example.moviesta.domain.usecase.movie.remote

import com.example.moviesta.data.remote.dto.details.DetailsResponse
import com.example.moviesta.domain.repository.MoviestaRepository
import kotlinx.coroutines.flow.Flow

class GetMovieDetailsUseCase (
    private val moviestaRepository: MoviestaRepository
) {
    suspend operator fun invoke(movieId: Int): Flow<DetailsResponse> {
        return moviestaRepository.getMovieDetails(movieId)
    }
}