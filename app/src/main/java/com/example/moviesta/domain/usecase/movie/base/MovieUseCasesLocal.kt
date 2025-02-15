package com.example.moviesta.domain.usecase.movie.base

import com.example.moviesta.domain.usecase.movie.local.DeleteMovieUseCase
import com.example.moviesta.domain.usecase.movie.local.GetMovieBookmarkedDetailsUseCase
import com.example.moviesta.domain.usecase.movie.local.GetMoviesBookmarkedUseCase
import com.example.moviesta.domain.usecase.movie.local.InsertMovieUseCase

data class MovieUseCasesLocal (
    val insertMovieUseCase: InsertMovieUseCase,
    val deleteMovieUseCase: DeleteMovieUseCase,
    val getMoviesBookmarkedUseCase: GetMoviesBookmarkedUseCase,
    val getMovieBookmarkedDetailsUseCase: GetMovieBookmarkedDetailsUseCase
)
