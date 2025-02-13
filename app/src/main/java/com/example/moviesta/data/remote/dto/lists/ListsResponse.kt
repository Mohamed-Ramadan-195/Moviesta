package com.example.moviesta.data.remote.dto.lists

import com.example.moviesta.domain.model.Movies
import com.google.gson.annotations.SerializedName

data class ListsResponse (
    val dates: Dates,
    val page: Int,
    val movies: List<Movies>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)