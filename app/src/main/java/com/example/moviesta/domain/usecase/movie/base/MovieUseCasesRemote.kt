package com.example.moviesta.domain.usecase.movie.base

import com.example.moviesta.domain.usecase.movie.remote.GetGenresListUseCase
import com.example.moviesta.domain.usecase.movie.remote.GetMovieDetailsUseCase
import com.example.moviesta.domain.usecase.movie.remote.GetMovieListsUseCase
import com.example.moviesta.domain.usecase.movie.remote.GetMoviesByGenreUseCase
import com.example.moviesta.domain.usecase.movie.remote.SearchMovieUseCase

data class MovieUseCasesRemote (
    val getMovieListsUseCase: GetMovieListsUseCase,
    val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    val searchMovieUseCase: SearchMovieUseCase,
    val getGenresListUseCase: GetGenresListUseCase,
    val getMoviesByGenreUseCase: GetMoviesByGenreUseCase
)
