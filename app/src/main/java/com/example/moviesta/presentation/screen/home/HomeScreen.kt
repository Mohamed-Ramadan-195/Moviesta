package com.example.moviesta.presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.moviesta.presentation.common.SearchBarItem
import com.example.moviesta.presentation.common.TextHeadline
import com.example.moviesta.presentation.common.SpacerHeight
import com.example.moviesta.presentation.common.TextAddress
import com.example.moviesta.presentation.common.TextMedium
import com.example.moviesta.util.Dimen.SmallSpace
import com.example.moviesta.util.Dimen.UnderMediumSpace
import com.example.moviesta.util.Dimen.MediumSpace
import com.example.moviesta.util.Dimen.ExtraLargeSpace

@Composable
fun HomeScreen (
    navigateToSearch: () -> Unit,
    navigateToDetails: (Int) -> Unit
) {
    val homeViewModel: HomeViewModel = hiltViewModel()

    val movieNowPlayingState by homeViewModel.movieNowPlayingState
    val moviePopularState by homeViewModel.moviePopularState
    val movieTopRatedState by homeViewModel.movieTopRatedState
    val movieUpcomingState by homeViewModel.movieUpcomingState

    HomeScreenContent (
        moviesNowPlaying = movieNowPlayingState.movies,
        moviesPopular = moviePopularState.movies,
        moviesTopRated = movieTopRatedState.movies,
        moviesUpcoming = movieUpcomingState.movies,
        navigateToSearch = navigateToSearch,
        navigateToDetails = navigateToDetails
    )
}

@Composable
fun HomeScreenContent (
    moviesNowPlaying: List<Movies>,
    moviesPopular: List<Movies>,
    moviesTopRated: List<Movies>,
    moviesUpcoming: List<Movies>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Int) -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(SmallSpace)
    ) {
        TextAddress("Moviesta")
        TextMedium("Discover Your Next Favorite Movie")
        SpacerHeight(SmallSpace)
        SearchBarItem (
            modifier = Modifier.fillMaxWidth(),
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearch = {},
            onClick = { navigateToSearch() }
        )
        SpacerHeight(MediumSpace)
        TextHeadline("Now Playing")
        SpacerHeight(UnderMediumSpace)
        LazyRow (
            modifier = Modifier
        ) {
            items(moviesNowPlaying.size) { index ->
                MovieItem(
                    movie = moviesNowPlaying[index],
                    navigateToDetails = {
                        navigateToDetails(moviesNowPlaying[index].id)
                    }
                )
            }
        }
        SpacerHeight(ExtraLargeSpace)
        TextHeadline("Popular")
        SpacerHeight(UnderMediumSpace)
        LazyRow (
            modifier = Modifier
        ) {
            items(moviesPopular.size) { index ->
                MovieItem(
                    movie = moviesPopular[index],
                    navigateToDetails = {
                        navigateToDetails(moviesPopular[index].id)
                    }
                )
            }
        }
        SpacerHeight(ExtraLargeSpace)
        TextHeadline("Top Rated")
        SpacerHeight(UnderMediumSpace)
        LazyRow (
            modifier = Modifier
        ) {
            items(moviesTopRated.size) { index ->
                MovieItem(
                    movie = moviesTopRated[index],
                    navigateToDetails = {
                        navigateToDetails(moviesTopRated[index].id)
                    }
                )
            }
        }
        SpacerHeight(ExtraLargeSpace)
        TextHeadline("Upcoming")
        SpacerHeight(UnderMediumSpace)
        LazyRow (
            modifier = Modifier
        ) {
            items(moviesUpcoming.size) { index ->
                MovieItem(
                    movie = moviesUpcoming[index],
                    navigateToDetails = {
                        navigateToDetails(moviesUpcoming[index].id)
                    }
                )
            }
        }
    }
}