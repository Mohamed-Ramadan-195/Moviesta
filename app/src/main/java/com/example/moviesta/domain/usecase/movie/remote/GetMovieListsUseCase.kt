package com.example.moviesta.domain.usecase.movie.remote

import com.example.moviesta.domain.model.Movie
import com.example.moviesta.domain.repository.MoviestaRepository
import kotlinx.coroutines.flow.Flow

class GetMovieListsUseCase (
    private val moviestaRepository: MoviestaRepository
) {
    suspend operator fun invoke(endPoint: String): Flow<List<Movie>> {
        return moviestaRepository.getMovieLists(endPoint)
    }
}