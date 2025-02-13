package com.example.moviesta.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesta.R
import com.example.moviesta.domain.model.Movies
import com.example.moviesta.ui.theme.SecondaryColor
import com.example.moviesta.util.Constant
import com.example.moviesta.util.Dimen
import kotlin.math.round

@Composable
fun MovieItem (
    modifier: Modifier = Modifier,
    movie: Movies
) {
    val context = LocalContext.current
    Column (
        modifier = modifier.padding(end = Dimen.SmallSpace),
        horizontalAlignment = Alignment.Start
    ) {
        Box (
            modifier = Modifier
                .size(320.dp, height = 160.dp)
                .clip(RoundedCornerShape(Dimen.MediumSpace))
                .border(1.dp, SecondaryColor, RoundedCornerShape(Dimen.MediumSpace))
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(context)
                    .data("${Constant.BASE_IMAGE_URL}${movie.posterPath}")
                    .crossfade(true)
                    .build(),
                contentDescription = "Movie Poster",
                contentScale = ContentScale.FillBounds
            )
            MoviestaIconButton (
                modifier.align(Alignment.TopEnd),
                icon = R.drawable.ic_bookmark,
                onClick = {}
            )
        }
        SpacerHeight(Dimen.MediumSpace)
        TextMedium(text = movie.title)
        RatingBarItem(ratingAverage = movie.voteAverage)
    }
}

@Composable
fun RatingBarItem (
    ratingAverage: Double,
    stars: Int = 5
) {
    val rating = round(ratingAverage) / 2

    Row {
        for (i in 1..stars) {
            Image (
                modifier = Modifier.padding(end = Dimen.ExtraSmallSpace),
                painter = painterResource (
                    if (i <= rating) R.drawable.ic_star_full
                    else if ((i - 0.5f).toDouble() == rating) R.drawable.ic_star_half
                    else R.drawable.ic_star_empty
                ),
                contentDescription = "Star"
            )
        }
    }
}

@Composable
@Preview
fun RatingBarItemPreview() {
    RatingBarItem(ratingAverage = 8.7)
}