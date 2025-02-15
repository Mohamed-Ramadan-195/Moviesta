package com.example.moviesta.domain.usecase.movie.remote

import com.example.moviesta.domain.model.Movie
import com.example.moviesta.domain.repository.MoviestaRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesByGenreUseCase (
    private val moviestaRepository: MoviestaRepository
) {
    suspend operator fun invoke(genreId: Int): Flow<List<Movie>> {
        return moviestaRepository.getMoviesByGenre(genreId)
    }
}