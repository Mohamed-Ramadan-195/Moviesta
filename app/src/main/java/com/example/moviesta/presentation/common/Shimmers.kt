package com.example.moviesta.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.moviesta.presentation.Dimen.ExtraSmallSpace
import com.example.moviesta.presentation.Dimen.LargeSpace
import com.example.moviesta.presentation.Dimen.MediumSpace
import com.example.moviesta.presentation.Dimen.SmallSpace
import com.example.moviesta.util.shimmerEffect

@Composable
fun MoviesListsShimmerEffect (
    isLoading: Boolean
) {
    if (isLoading) {
        Column (
            modifier = Modifier.padding(MediumSpace)
        ) {
            SpacerHeight(SmallSpace)
            Box (
                modifier = Modifier
                    .size(width = 320.dp, height = 160.dp)
                    .clip(RoundedCornerShape(MediumSpace))
                    .shimmerEffect()
            )
            SpacerHeight(SmallSpace)
            Box (
                modifier = Modifier
                    .size(width = 240.dp, height = 20.dp)
                    .clip(RectangleShape)
                    .shimmerEffect()
            )
            SpacerHeight(ExtraSmallSpace)
            Box (
                modifier = Modifier
                    .size(width = 160.dp, height = 20.dp)
                    .clip(RectangleShape)
                    .shimmerEffect()
            )
        }
        SpacerHeight(LargeSpace)
    }
}