package com.example.moviesta.domain.usecase.movie.local

import com.example.moviesta.domain.model.Movie
import com.example.moviesta.domain.repository.MoviestaRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesBookmarkedUseCase (
    private val moviestaRepository: MoviestaRepository
) {
    operator fun invoke(): Flow<List<Movie>> {
        return moviestaRepository.getMoviesBookmarked()
    }
}