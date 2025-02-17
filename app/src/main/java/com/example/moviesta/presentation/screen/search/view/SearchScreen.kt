package com.example.moviesta.presentation.screen.search.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviesta.R
import com.example.moviesta.domain.model.Movie
import com.example.moviesta.presentation.common.EmptyScreen
import com.example.moviesta.presentation.common.MovieItemVertical
import com.example.moviesta.presentation.common.SearchBarItem
import com.example.moviesta.presentation.common.SpacerHeight
import com.example.moviesta.presentation.common.TextAddress
import com.example.moviesta.presentation.common.TextMedium
import com.example.moviesta.presentation.screen.bookmark.state.BookmarkEvent
import com.example.moviesta.presentation.screen.bookmark.viewmodel.BookmarkViewModel
import com.example.moviesta.presentation.screen.search.state.SearchEvent
import com.example.moviesta.presentation.screen.search.state.SearchState
import com.example.moviesta.presentation.screen.search.viewmodel.SearchViewModel
import com.example.moviesta.ui.theme.SecondaryColor
import com.example.moviesta.util.Dimen.ExtraSmallSpace
import com.example.moviesta.util.Dimen.LargeSpace
import com.example.moviesta.util.Dimen.MediumSpace
import com.example.moviesta.util.Dimen.SmallSpace
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun SearchScreen (
    navigateToDetails: (Int, Movie) -> Unit
) {
    val searchViewModel: SearchViewModel = hiltViewModel()
    val bookmarkViewModel: BookmarkViewModel = hiltViewModel()

    val searchState = searchViewModel.searchState.value
    val coroutineScope = rememberCoroutineScope()
    var searchJob by remember { mutableStateOf<Job?>(null) }
    val bookmarkedMovies = bookmarkViewModel.bookmarkedMovies

    if (bookmarkViewModel.sideEffect != null) {
        Toast.makeText(LocalContext.current, bookmarkViewModel.sideEffect, Toast.LENGTH_SHORT).show()
        bookmarkViewModel.onEvent(BookmarkEvent.RemoveSideEffect)
    }

    SearchScreenContent (
        searchState = searchState,
        searchEvent = { event ->
            if (event is SearchEvent.UpdateSearchQuery) {
                searchJob?.cancel()
                searchJob = coroutineScope.launch {
                    searchViewModel.onEvent(event)
                }
            } else {
                searchViewModel.onEvent(event)
            }
        },
        navigateToDetails = navigateToDetails,
        bookmarkedMovies = bookmarkedMovies,
        bookmarkEvent = bookmarkViewModel::onEvent
    )
}

@Composable
private fun SearchScreenContent (
    searchState: SearchState,
    searchEvent: (SearchEvent) -> Unit,
    navigateToDetails: (Int, Movie) -> Unit,
    bookmarkedMovies: Set<Int>,
    bookmarkEvent: (BookmarkEvent) -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
            .safeDrawingPadding()
            .safeContentPadding()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(MediumSpace)
    ) {
        TextAddress("Moviesta")
        TextMedium("Explore the world of cinema")
        SpacerHeight(MediumSpace)
        SearchBarItem (
            modifier = Modifier,
            text = searchState.searchQuery,
            readOnly = false,
            onValueChange = { searchEvent(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = { searchEvent(SearchEvent.SearchMovies) }
        )
        SpacerHeight(MediumSpace)
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(SecondaryColor, RectangleShape)
        )
        SpacerHeight(MediumSpace)
        if (searchState.movies.isEmpty()) {
            EmptyScreen (
                image = R.drawable.search_placeholder,
                title = "Search for movies 🎬",
                body = "Looking for something specific?\nType here !"
            )
        }
        LazyVerticalStaggeredGrid (
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.fillMaxSize().fillMaxWidth(),
            contentPadding = PaddingValues(ExtraSmallSpace),
            verticalItemSpacing = LargeSpace
        ) {
            items(searchState.movies.size) { item ->
                val movie = searchState.movies[item]
                val isBookmarked = bookmarkedMovies.contains(movie.id)
                MovieItemVertical (
                    modifier = Modifier.padding(SmallSpace),
                    movie = searchState.movies[item],
                    navigateToDetails = { _, _ ->
                        navigateToDetails(movie.id, movie)
                    },
                    isBookmarked = isBookmarked,
                    onClick = { bookmarkEvent(BookmarkEvent.OperationsInMovie(movie = movie)) }
                )
            }
        }
    }
}