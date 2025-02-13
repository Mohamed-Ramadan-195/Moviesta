package com.example.moviesta.domain.usecase.movie

import com.example.moviesta.domain.model.Movies
import com.example.moviesta.domain.repository.MoviestaRepository
import kotlinx.coroutines.flow.Flow

class GetMovieListsUseCase (
    private val moviestaRepository: MoviestaRepository
) {
    suspend operator fun invoke(endPoint: String): Flow<List<Movies>> {
        return moviestaRepository.getMovieLists(endPoint)
    }
}