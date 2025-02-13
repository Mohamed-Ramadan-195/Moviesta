package com.example.moviesta.presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviesta.domain.model.Movies
import com.example.moviesta.presentation.common.MovieItem
import com.example.moviesta.presentation.common.TextHeadline
import com.example.moviesta.presentation.common.SpacerHeight
import com.example.moviesta.util.Dimen

@Composable
fun HomeScreen() {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val movieNowPlayingState by homeViewModel.movieNowPlayingState
    val moviePopularState by homeViewModel.moviePopularState
    val movieTopRatedState by homeViewModel.movieTopRatedState
    val movieUpcomingState by homeViewModel.movieUpcomingState
    HomeScreenContent (
        moviesNowPlaying = movieNowPlayingState.movies,
        moviesPopular = moviePopularState.movies,
        moviesTopRated = movieTopRatedState.movies,
        moviesUpcoming = movieUpcomingState.movies
    )
}

@Composable
fun HomeScreenContent (
    moviesNowPlaying: List<Movies>,
    moviesPopular: List<Movies>,
    moviesTopRated: List<Movies>,
    moviesUpcoming: List<Movies>
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(Dimen.SmallSpace)
    ) {
        TextHeadline("Now Playing")
        SpacerHeight(Dimen.UnderMediumSpace)
        LazyRow (
            modifier = Modifier
        ) {
            items(moviesNowPlaying.size) { index ->
                MovieItem(movie = moviesNowPlaying[index])
            }
        }
        SpacerHeight(Dimen.ExtraLargeSpace)
        TextHeadline("Popular")
        SpacerHeight(Dimen.UnderMediumSpace)
        LazyRow (
            modifier = Modifier
        ) {
            items(moviesPopular.size) { index ->
                MovieItem(movie = moviesPopular[index])
            }
        }
        SpacerHeight(Dimen.ExtraLargeSpace)
        TextHeadline("Top Rated")
        SpacerHeight(Dimen.UnderMediumSpace)
        LazyRow (
            modifier = Modifier
        ) {
            items(moviesTopRated.size) { index ->
                MovieItem(movie = moviesTopRated[index])
            }
        }
        SpacerHeight(Dimen.ExtraLargeSpace)
        TextHeadline("Upcoming")
        SpacerHeight(Dimen.UnderMediumSpace)
        LazyRow (
            modifier = Modifier
        ) {
            items(moviesUpcoming.size) { index ->
                MovieItem(movie = moviesUpcoming[index])
            }
        }
    }
}