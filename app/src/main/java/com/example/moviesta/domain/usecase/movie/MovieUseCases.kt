package com.example.moviesta.domain.usecase.movie

data class MovieUseCases (
    val getMovieListsUseCase: GetMovieListsUseCase,
    val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    val searchMovieUseCase: SearchMovieUseCase,
    val getGenresListUseCase: GetGenresListUseCase,
    val getMoviesByGenreUseCase: GetMoviesByGenreUseCase
)
