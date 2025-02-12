package com.example.moviesta.data.repository

import com.example.moviesta.data.local.MoviestaDao
import com.example.moviesta.data.remote.api.MoviestaApi
import com.example.moviesta.domain.repository.MoviestaRepository
import javax.inject.Inject

class MoviestaRepositoryImpl @Inject constructor (
    private val moviestaDao: MoviestaDao,
    private val moviestaApi: MoviestaApi
) : MoviestaRepository {

}