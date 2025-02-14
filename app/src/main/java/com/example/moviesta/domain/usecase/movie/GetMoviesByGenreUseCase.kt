package com.example.moviesta.domain.usecase.movie

import com.example.moviesta.domain.model.Movies
import com.example.moviesta.domain.repository.MoviestaRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesByGenreUseCase (
    private val moviestaRepository: MoviestaRepository
) {
    suspend operator fun invoke(genreId: Int): Flow<List<Movies>> {
        return moviestaRepository.getMoviesByGenre(genreId)
    }
}