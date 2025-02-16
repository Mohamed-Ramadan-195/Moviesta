package com.example.moviesta.presentation.navigation.nav_graph

import com.example.moviesta.util.Constant.APP_START_NAVIGATION
import com.example.moviesta.util.Constant.BOOKMARK_SCREEN
import com.example.moviesta.util.Constant.DETAILS_SCREEN
import com.example.moviesta.util.Constant.DISCOVER_SCREEN
import com.example.moviesta.util.Constant.HOME_SCREEN
import com.example.moviesta.util.Constant.MOVIESTA_NAVIGATION
import com.example.moviesta.util.Constant.MOVIESTA_NAVIGATOR
import com.example.moviesta.util.Constant.ON_BOARDING_SCREEN
import com.example.moviesta.util.Constant.SEARCH_SCREEN

sealed class Route (
    val route: String
) {
    // Navigation
    data object AppStartNavigation: Route(APP_START_NAVIGATION)
    data object MoviestaNavigation: Route(MOVIESTA_NAVIGATION)
    data object MoviestaNavigator: Route(MOVIESTA_NAVIGATOR)

    // Screen
    data object OnBoardingScreen: Route(ON_BOARDING_SCREEN)
    data object HomeScreen: Route(HOME_SCREEN)
    data object SearchScreen: Route(SEARCH_SCREEN)
    data object BookmarkScreen: Route(BOOKMARK_SCREEN)
    data object DiscoverScreen: Route(DISCOVER_SCREEN)
    data object DetailsScreen: Route(DETAILS_SCREEN)
}
