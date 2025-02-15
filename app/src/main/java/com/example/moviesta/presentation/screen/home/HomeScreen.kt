package com.example.moviesta.presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviesta.domain.model.Movie
import com.example.moviesta.presentation.common.MovieItem
import com.example.moviesta.presentation.common.MoviestaTextButton
import com.example.moviesta.presentation.common.SearchBarItem
import com.example.moviesta.presentation.common.TextHeadline
import com.example.moviesta.presentation.common.SpacerHeight
import com.example.moviesta.presentation.common.TextAddress
import com.example.moviesta.presentation.common.TextMedium
import com.example.moviesta.presentation.screen.bookmark.BookmarkEvent
import com.example.moviesta.presentation.screen.bookmark.BookmarkViewModel
import com.example.moviesta.ui.theme.PrimaryColor
import com.example.moviesta.util.Dimen.ExtraSmallSpace
import com.example.moviesta.util.Dimen.SmallSpace
import com.example.moviesta.util.Dimen.MediumSpace
import com.example.moviesta.util.Dimen.ExtraLargeSpace

@Composable
fun HomeScreen (
    navigateToSearch: () -> Unit,
    navigateToDetails: (Int, Movie) -> Unit,
    navigateToDiscover: () -> Unit
) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val bookmarkViewModel: BookmarkViewModel = hiltViewModel()

    val movieNowPlayingState by homeViewModel.movieNowPlayingState
    val moviePopularState by homeViewModel.moviePopularState
    val movieTopRatedState by homeViewModel.movieTopRatedState
    val movieUpcomingState by homeViewModel.movieUpcomingState

    if (bookmarkViewModel.sideEffect != null) {
        bookmarkViewModel.onEvent(BookmarkEvent.RemoveSideEffect)
    }
    val bookmarkedMovies = bookmarkViewModel.bookmarkedMovies

    HomeScreenContent (
        moviesNowPlaying = movieNowPlayingState.movies,
        moviesPopular = moviePopularState.movies,
        moviesTopRated = movieTopRatedState.movies,
        moviesUpcoming = movieUpcomingState.movies,
        navigateToSearch = navigateToSearch,
        navigateToDetails = navigateToDetails,
        navigateToDiscover = navigateToDiscover,
        bookmarkEvent = bookmarkViewModel::onEvent,
        bookmarkedMovies = bookmarkedMovies
    )
}

@Composable
fun HomeScreenContent (
    moviesNowPlaying: List<Movie>,
    moviesPopular: List<Movie>,
    moviesTopRated: List<Movie>,
    moviesUpcoming: List<Movie>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Int, Movie) -> Unit,
    navigateToDiscover: () -> Unit,
    bookmarkEvent: (BookmarkEvent) -> Unit,
    bookmarkedMovies: Set<Int>
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .navigationBarsPadding()
            .safeContentPadding()
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

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextHeadline("Now Playing")
            MoviestaTextButton(
                modifier = Modifier.padding(end = ExtraSmallSpace),
                text = "See More",
                color = PrimaryColor,
                onClick = { navigateToDiscover() }
            )
        }
        SpacerHeight(SmallSpace)
        LazyRow (
            modifier = Modifier
        ) {
            items(moviesNowPlaying.size) { index ->
                val movie = moviesNowPlaying[index]
                val isBookmarked = bookmarkedMovies.contains(movie.id)

                MovieItem(
                    movie = movie,
                    navigateToDetails = { _, _ ->
                        navigateToDetails(movie.id, movie)
                    },
                    onClick = { bookmarkEvent(BookmarkEvent.OperationsInMovie(movie = movie)) },
                    isBookmarked = isBookmarked
                )
            }
        }
        SpacerHeight(ExtraLargeSpace)

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextHeadline("Popular")
            MoviestaTextButton(
                modifier = Modifier.padding(end = ExtraSmallSpace),
                text = "SEE MORE",
                color = PrimaryColor,
                onClick = { navigateToDiscover() }
            )
        }
        SpacerHeight(SmallSpace)
        LazyRow (
            modifier = Modifier
        ) {
            items(moviesPopular.size) { index ->
                val movie = moviesPopular[index]
                val isBookmarked = bookmarkedMovies.contains(movie.id)
                MovieItem(
                    movie = movie,
                    navigateToDetails = { _, _ ->
                        navigateToDetails(movie.id, movie)
                    },
                    onClick = { bookmarkEvent(BookmarkEvent.OperationsInMovie(movie = movie)) },
                    isBookmarked = isBookmarked
                )
            }
        }
        SpacerHeight(ExtraLargeSpace)

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextHeadline("Top Rated")
            MoviestaTextButton(
                modifier = Modifier.padding(end = ExtraSmallSpace),
                text = "SEE MORE",
                color = PrimaryColor,
                onClick = { navigateToDiscover() }
            )
        }
        SpacerHeight(SmallSpace)
        LazyRow (
            modifier = Modifier
        ) {
            items(moviesTopRated.size) { index ->
                val movie = moviesTopRated[index]
                val isBookmarked = bookmarkedMovies.contains(movie.id)
                MovieItem(
                    movie = movie,
                    navigateToDetails = { _, _ ->
                        navigateToDetails(movie.id, movie)
                    },
                    onClick = { bookmarkEvent(BookmarkEvent.OperationsInMovie(movie = movie)) },
                    isBookmarked = isBookmarked
                )
            }
        }
        SpacerHeight(ExtraLargeSpace)

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextHeadline("Upcoming")
            MoviestaTextButton(
                modifier = Modifier.padding(end = ExtraSmallSpace),
                text = "SEE MORE",
                color = PrimaryColor,
                onClick = { navigateToDiscover() }
            )
        }
        SpacerHeight(SmallSpace)
        LazyRow (
            modifier = Modifier
        ) {
            items(moviesUpcoming.size) { index ->
                val movie = moviesUpcoming[index]
                val isBookmarked = bookmarkedMovies.contains(movie.id)
                MovieItem(
                    movie = movie,
                    navigateToDetails = { _, _ ->
                        navigateToDetails(movie.id, movie)
                    },
                    onClick = { bookmarkEvent(BookmarkEvent.OperationsInMovie(movie = movie)) },
                    isBookmarked = isBookmarked
                )
            }
        }
    }
}