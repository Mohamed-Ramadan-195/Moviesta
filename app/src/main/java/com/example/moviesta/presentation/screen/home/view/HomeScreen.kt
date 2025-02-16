package com.example.moviesta.presentation.screen.home.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviesta.domain.model.Movie
import com.example.moviesta.presentation.common.MovieListsInHomeItem
import com.example.moviesta.presentation.common.MoviesListsShimmerEffect
import com.example.moviesta.presentation.common.MoviestaTextButton
import com.example.moviesta.presentation.common.SearchBarItem
import com.example.moviesta.presentation.common.TextHeadline
import com.example.moviesta.presentation.common.SpacerHeight
import com.example.moviesta.presentation.common.TextAddress
import com.example.moviesta.presentation.common.TextMedium
import com.example.moviesta.presentation.screen.bookmark.state.BookmarkEvent
import com.example.moviesta.presentation.screen.bookmark.viewmodel.BookmarkViewModel
import com.example.moviesta.presentation.screen.home.state.MovieState
import com.example.moviesta.presentation.screen.home.viewmodel.HomeViewModel
import com.example.moviesta.ui.theme.PrimaryColor
import com.example.moviesta.util.Dimen.ExtraSmallSpace
import com.example.moviesta.util.Dimen.SmallSpace
import com.example.moviesta.util.Dimen.MediumSpace
import com.example.moviesta.util.Dimen.ExtraLargeSpace
import kotlinx.coroutines.delay

@Composable
fun HomeScreen (
    navigateToSearch: () -> Unit,
    navigateToDetails: (Int, Movie) -> Unit,
    navigateToDiscover: () -> Unit
) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val bookmarkViewModel: BookmarkViewModel = hiltViewModel()

    val movieState by homeViewModel.movieState

    if (bookmarkViewModel.sideEffect != null) {
        Toast.makeText(LocalContext.current, bookmarkViewModel.sideEffect, Toast.LENGTH_SHORT).show()
        bookmarkViewModel.onEvent(BookmarkEvent.RemoveSideEffect)
    }
    val bookmarkedMovies = bookmarkViewModel.bookmarkedMovies

    HomeScreenContent (
        movieState = movieState,
        navigateToSearch = navigateToSearch,
        navigateToDetails = navigateToDetails,
        navigateToDiscover = navigateToDiscover,
        bookmarkEvent = bookmarkViewModel::onEvent,
        bookmarkedMovies = bookmarkedMovies
    )
}

@Composable
private fun HomeScreenContent (
    movieState: MovieState,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Int, Movie) -> Unit,
    navigateToDiscover: () -> Unit,
    bookmarkEvent: (BookmarkEvent) -> Unit,
    bookmarkedMovies: Set<Int>
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
            .verticalScroll(rememberScrollState())
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

        var isLoading by remember { mutableStateOf(true) }
        LaunchedEffect(key1 = true) {
            delay(3000)
            isLoading = false
        }

        repeat(4) {
            LazyRow {
                items(4) {
                    MoviesListsShimmerEffect(isLoading = isLoading)
                }
            }
        }

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
        MovieListsInHomeItem (
            movies = movieState.moviesNowPlaying,
            bookmarkedMovies = bookmarkedMovies,
            navigateToDetails = navigateToDetails,
            bookmarkEvent = bookmarkEvent
        )
        SpacerHeight(ExtraLargeSpace)

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextHeadline("Popular")
            MoviestaTextButton(
                modifier = Modifier.padding(end = ExtraSmallSpace),
                text = "See More",
                color = PrimaryColor,
                onClick = { navigateToDiscover() }
            )
        }
        SpacerHeight(SmallSpace)
        MovieListsInHomeItem (
            movies = movieState.moviesPopular,
            bookmarkedMovies = bookmarkedMovies,
            navigateToDetails = navigateToDetails,
            bookmarkEvent = bookmarkEvent
        )
        SpacerHeight(ExtraLargeSpace)

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextHeadline("Top Rated")
            MoviestaTextButton(
                modifier = Modifier.padding(end = ExtraSmallSpace),
                text = "See More",
                color = PrimaryColor,
                onClick = { navigateToDiscover() }
            )
        }
        SpacerHeight(SmallSpace)
        MovieListsInHomeItem (
            movies = movieState.moviesTopRated,
            bookmarkedMovies = bookmarkedMovies,
            navigateToDetails = navigateToDetails,
            bookmarkEvent = bookmarkEvent
        )
        SpacerHeight(ExtraLargeSpace)

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextHeadline("Upcoming")
            MoviestaTextButton(
                modifier = Modifier.padding(end = ExtraSmallSpace),
                text = "See More",
                color = PrimaryColor,
                onClick = { navigateToDiscover() }
            )
        }
        SpacerHeight(SmallSpace)
        MovieListsInHomeItem (
            movies = movieState.moviesUpcoming,
            bookmarkedMovies = bookmarkedMovies,
            navigateToDetails = navigateToDetails,
            bookmarkEvent = bookmarkEvent
        )
    }
}