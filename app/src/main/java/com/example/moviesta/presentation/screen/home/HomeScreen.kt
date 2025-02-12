package com.example.moviesta.presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.moviesta.presentation.common.TextHeadline
import com.example.moviesta.presentation.common.SpacerHeight
import com.example.moviesta.util.Dimen

@Composable
fun HomeScreen (

) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(Dimen.SmallSpace)
    ) {
        TextHeadline("Now Playing")
        SpacerHeight(Dimen.ExtraSmallSpace)
    }
}