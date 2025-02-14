package com.example.moviesta.domain.usecase.movie

import com.example.moviesta.domain.model.Movies
import com.example.moviesta.domain.repository.MoviestaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor (
    private val moviestaRepository: MoviestaRepository
) {
    suspend operator fun invoke(query: String): Flow<List<Movies>> {
        return moviestaRepository.searchMovie(query)
    }
}