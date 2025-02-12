package com.example.moviesta.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesta.util.Dimen

@Composable
fun MovieItem (
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        AsyncImage (
            modifier = Modifier.size(width = 300.dp, height = 150.dp),
            model = ImageRequest.Builder(context)
                .crossfade(true)
                .build(),
            contentDescription = "Movie Poster",
            contentScale = ContentScale.FillWidth
        )
        SpacerHeight(Dimen.ExtraSmallSpace)
        TextMedium (text = "")
    }
}