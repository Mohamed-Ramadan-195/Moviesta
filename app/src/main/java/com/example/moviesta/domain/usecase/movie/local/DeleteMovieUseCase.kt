package com.example.moviesta.domain.usecase.movie.local

import com.example.moviesta.domain.model.Movie
import com.example.moviesta.domain.repository.MoviestaRepository

class DeleteMovieUseCase (
    private val moviestaRepository: MoviestaRepository
) {
    suspend operator fun invoke(movie: Movie) {
        moviestaRepository.deleteMovie(movie = movie)
    }
}