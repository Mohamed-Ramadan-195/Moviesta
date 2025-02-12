package com.example.moviesta.presentation.navigation.nav_graph

import com.example.moviesta.util.Constant

sealed class Route (
    val route: String
) {
    // Navigation
    data object AppStartNavigation: Route(route = Constant.APP_START_NAVIGATION)
    data object MoviestaNavigation: Route(route = Constant.MOVIESTA_NAVIGATION)
    data object MoviestaNavigator: Route(route = Constant.MOVIESTA_NAVIGATOR)

    // Screen
    data object OnBoardingScreen: Route(route = Constant.ON_BOARDING_SCREEN)
    data object HomeScreen: Route(route = Constant.HOME_SCREEN)
    data object SearchScreen: Route(route = Constant.SEARCH_SCREEN)
    data object BookmarkScreen: Route(route = Constant.BOOKMARK_SCREEN)
    data object DiscoverScreen: Route(route = Constant.DISCOVER_SCREEN)
    data object DetailsScreen: Route(route = Constant.DETAILS_SCREEN)
}
