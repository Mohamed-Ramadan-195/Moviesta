package com.example.moviesta.data.remote.api

import com.example.moviesta.data.remote.dto.NowPlayingRequest
import com.example.moviesta.util.Constant
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviestaApi {
    @GET("movie/{endPoint}")
    suspend fun getMovieLists (
        @Path("endPoint") endPoint: String,
        @Query("api_key") apiKey: String = Constant.API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ) : NowPlayingRequest
}