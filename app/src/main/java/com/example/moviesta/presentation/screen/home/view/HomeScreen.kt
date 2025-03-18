package com.example.moviesta.presentation.screen.home.view

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviesta.domain.model.Movie
import com.example.moviesta.presentation.common.MovieListsInHomeItem
import com.example.moviesta.presentation.common.MovieListsItemAdress
import com.example.moviesta.presentation.common.MoviesListsShimmerEffect
import com.example.moviesta.presentation.common.SearchBarItem
import com.example.moviesta.presentation.common.SpacerHeight
import com.example.moviesta.presentation.common.TextAddress
import com.example.moviesta.presentation.common.TextMedium
import com.example.moviesta.presentation.screen.bookmark.state.BookmarkEvent
import com.example.moviesta.presentation.screen.bookmark.viewmodel.BookmarkViewModel
import com.example.moviesta.presentation.screen.home.state.MovieState
import com.example.moviesta.presentation.screen.home.viewmodel.HomeViewModel
import com.example.moviesta.presentation.Dimen.ExtraSmallSpace
import com.example.moviesta.presentation.Dimen.SmallSpace
import com.example.moviesta.presentation.Dimen.MediumSpace
import com.example.moviesta.presentation.Dimen.UnderMediumSpace
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
    val firstLoad = homeViewModel.isFirstLoad.value

    HomeScreenContent (
        movieState = movieState,
        navigateToSearch = navigateToSearch,
        navigateToDetails = navigateToDetails,
        navigateToDiscover = navigateToDiscover,
        bookmarkEvent = bookmarkViewModel::onEvent,
        bookmarkedMovies = bookmarkedMovies,
        firstLoad = firstLoad,
        setFirstLoadCompleted = homeViewModel::setFirstLoadCompleted
    )
}

@Composable
private fun HomeScreenContent (
    movieState: MovieState,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Int, Movie) -> Unit,
    navigateToDiscover: () -> Unit,
    bookmarkEvent: (BookmarkEvent) -> Unit,
    bookmarkedMovies: Set<Int>,
    firstLoad: Boolean,
    setFirstLoadCompleted: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
            .verticalScroll(rememberScrollState())
            .padding(vertical = SmallSpace, horizontal = UnderMediumSpace)
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

        var isLoading by remember { mutableStateOf(firstLoad) }

        LaunchedEffect(Unit) {
            if (isLoading) {
                delay(3000)
                isLoading = false
                setFirstLoadCompleted()
            }
        }

        repeat(4) {
            LazyRow (
                contentPadding = PaddingValues(ExtraSmallSpace)
            ) {
                items(4) {
                    MoviesListsShimmerEffect(isLoading = isLoading)
                }
            }
        }

        // List 1 : Now Playing
        MovieListsItemAdress (
            textHeadline = "Now Playing",
            navigateToDiscover = navigateToDiscover
        )
        SpacerHeight(SmallSpace)
        MovieListsInHomeItem (
            movies = movieState.moviesNowPlaying,
            bookmarkedMovies = bookmarkedMovies,
            navigateToDetails = navigateToDetails,
            bookmarkEvent = bookmarkEvent
        )
        SpacerHeight(MediumSpace)

        // List 2 : Popular
        MovieListsItemAdress (
            textHeadline = "Popular",
            navigateToDiscover = navigateToDiscover
        )
        SpacerHeight(SmallSpace)
        MovieListsInHomeItem (
            movies = movieState.moviesPopular,
            bookmarkedMovies = bookmarkedMovies,
            navigateToDetails = navigateToDetails,
            bookmarkEvent = bookmarkEvent
        )
        SpacerHeight(MediumSpace)

        // List 3 : Top Rated
        MovieListsItemAdress (
            textHeadline = "Top Rated",
            navigateToDiscover = navigateToDiscover
        )
        SpacerHeight(SmallSpace)
        MovieListsInHomeItem (
            movies = movieState.moviesTopRated,
            bookmarkedMovies = bookmarkedMovies,
            navigateToDetails = navigateToDetails,
            bookmarkEvent = bookmarkEvent
        )
        SpacerHeight(MediumSpace)

        // List 4 : Upcoming
        MovieListsItemAdress (
            textHeadline = "Upcoming",
            navigateToDiscover = navigateToDiscover
        )
        SpacerHeight(SmallSpace)
        MovieListsInHomeItem (
            movies = movieState.moviesUpcoming,
            bookmarkedMovies = bookmarkedMovies,
            navigateToDetails = navigateToDetails,
            bookmarkEvent = bookmarkEvent
        )
        SpacerHeight(MediumSpace)
    }
}