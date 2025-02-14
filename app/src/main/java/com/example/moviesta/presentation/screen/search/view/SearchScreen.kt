package com.example.moviesta.presentation.screen.search.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviesta.presentation.common.MovieItem
import com.example.moviesta.presentation.common.SearchBarItem
import com.example.moviesta.presentation.common.SpacerHeight
import com.example.moviesta.presentation.common.TextAddress
import com.example.moviesta.presentation.common.TextMedium
import com.example.moviesta.presentation.screen.search.state.SearchEvent
import com.example.moviesta.presentation.screen.search.state.SearchState
import com.example.moviesta.presentation.screen.search.viewmodel.SearchViewModel
import com.example.moviesta.ui.theme.SecondaryColor
import com.example.moviesta.util.Dimen.MediumSpace
import com.example.moviesta.util.Dimen.SmallSpace

@Composable
fun SearchScreen (
    navigateToDetails: (Int) -> Unit
) {
    val searchViewModel: SearchViewModel = hiltViewModel()
    val searchState = searchViewModel.searchState.value

    SearchScreenContent (
        searchState = searchState,
        searchEvent = searchViewModel::onEvent,
        navigateToDetails = navigateToDetails
    )
}

@Composable
fun SearchScreenContent (
    searchState: SearchState,
    searchEvent: (SearchEvent) -> Unit,
    navigateToDetails: (Int) -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .safeContentPadding()
            .padding(SmallSpace),
    ) {
        TextAddress("Moviesta")
        TextMedium("Explore the world of cinema - search now!")
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
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            items(searchState.movies.size) { item ->
                MovieItem (
                    modifier = Modifier.padding(SmallSpace),
                    movie = searchState.movies[item],
                    navigateToDetails = { navigateToDetails(searchState.movies[item].id) }
                )
            }
        }
    }
}