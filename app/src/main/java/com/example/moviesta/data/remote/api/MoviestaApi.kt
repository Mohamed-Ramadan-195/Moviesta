package com.example.moviesta.data.remote.api

import com.example.moviesta.data.remote.dto.details.DetailsResponse
import com.example.moviesta.data.remote.dto.lists.ListsResponse
import com.example.moviesta.data.remote.dto.search.MovieSearchResponse
import com.example.moviesta.util.Constant.API_KEY
import com.example.moviesta.util.Constant.ENGLISH_US
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviestaApi {
    @GET("movie/{endPoint}")
    suspend fun getMovieLists (
        @Path("endPoint") endPoint: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = ENGLISH_US,
        @Query("page") page: Int = 1
    ): ListsResponse

    @GET("movie/{movieId}")
    suspend fun getMovieDetails (
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = ENGLISH_US
    ): DetailsResponse

    @GET("search/movie")
    suspend fun searchMovie (
        @Query("api_key") apiKey: String = API_KEY,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("language") language: String = ENGLISH_US,
        @Query("page") page: Int = 1,
        @Query("query") query: String
    ): MovieSearchResponse
}