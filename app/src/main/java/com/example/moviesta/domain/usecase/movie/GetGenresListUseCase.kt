package com.example.moviesta.domain.usecase.movie

import com.example.moviesta.domain.model.Genre
import com.example.moviesta.domain.repository.MoviestaRepository
import kotlinx.coroutines.flow.Flow

class GetGenresListUseCase (
    private val moviestaRepository: MoviestaRepository
) {
    suspend operator fun invoke(): Flow<List<Genre>> {
        return moviestaRepository.getGenresList()
    }
}