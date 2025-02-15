package com.example.moviesta.domain.usecase.movie.local

import com.example.moviesta.domain.model.Movie
import com.example.moviesta.domain.repository.MoviestaRepository

class GetMovieBookmarkedDetailsUseCase (
    private val moviestaRepository: MoviestaRepository
) {
    suspend operator fun invoke(id: Int): Movie? {
        return moviestaRepository.getMovieBookmarkedDetails(id = id)
    }
}