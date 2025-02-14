package com.example.moviesta.data.remote.dto.genre

import com.example.moviesta.domain.model.Genre

data class GenreResponse(
    val genres: List<Genre>
)