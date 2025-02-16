package com.example.moviesta.util

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.example.moviesta.ui.theme.SecondaryColor
import com.example.moviesta.util.Constant.EMPTY_STRING

fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition(label = EMPTY_STRING)
    val alpha = transition.animateFloat (
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable (
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ), label = EMPTY_STRING
    ).value
    background(color = SecondaryColor.copy(alpha))
}