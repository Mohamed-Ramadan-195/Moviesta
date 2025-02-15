package com.example.moviesta.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesta.R
import com.example.moviesta.domain.model.Genre
import com.example.moviesta.domain.model.Movie
import com.example.moviesta.ui.theme.PrimaryColor
import com.example.moviesta.ui.theme.SecondaryColor
import com.example.moviesta.util.Constant
import com.example.moviesta.util.Dimen
import com.example.moviesta.util.Dimen.ExtraSmallSpace
import com.example.moviesta.util.Dimen.MediumSpace
import com.example.moviesta.util.Dimen.SmallSpace
import kotlin.math.round

@Composable
fun MovieItem (
    modifier: Modifier = Modifier,
    movie: Movie,
    navigateToDetails: (Int, Movie) -> Unit,
    onClick: () -> Unit,
    isBookmarked: Boolean
) {
    val context = LocalContext.current
    Column (
        modifier = modifier
            .padding(end = SmallSpace)
            .clickable { navigateToDetails(movie.id, movie) },
        horizontalAlignment = Alignment.Start
    ) {
        Box (
            modifier = Modifier
                .size(320.dp, height = 160.dp)
                .clip(RoundedCornerShape(MediumSpace))
                .border(1.dp, SecondaryColor, RoundedCornerShape(MediumSpace))
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
                icon = if (isBookmarked) R.drawable.ic_bookmark_focused else R.drawable.ic_bookmark,
                tint = if (isBookmarked) PrimaryColor else Color.White,
                onClick = { onClick() }
            )
        }
        SpacerHeight(MediumSpace)
        TextMedium(text = movie.title)
        RatingBarItem(ratingAverage = movie.voteAverage)
    }
}

/* ---------------------------------------- */

@Composable
fun RatingBarItem (
    ratingAverage: Double,
    stars: Int = 5
) {
    val rating = round(ratingAverage) / 2

    Row {
        for (i in 1..stars) {
            Image (
                modifier = Modifier.padding(end = ExtraSmallSpace),
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

/* ---------------------------------------- */

@Composable
fun SearchBarItem (
    modifier: Modifier = Modifier,
    text: String,
    readOnly: Boolean,
    onClick: (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isClicked = interactionSource.collectIsPressedAsState().value

    LaunchedEffect(key1 = isClicked) {
        if (isClicked) { onClick?.invoke() }
    }

    Box {
        OutlinedTextField (
            modifier = modifier.fillMaxWidth(),
            value = text,
            onValueChange = onValueChange,
            readOnly = readOnly,
            leadingIcon = {
                Icon (
                    modifier = Modifier.size(Dimen.LargeSpace),
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "Search Icon",
                    tint = Color.White
                )
            },
            placeholder = {
                Text (
                    text = "Search",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.Serif,
                    color = Color.White
                )
            },
            shape = RoundedCornerShape(MediumSpace),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearch() }),
            interactionSource = interactionSource,
            colors = OutlinedTextFieldDefaults.colors (
                unfocusedContainerColor = SecondaryColor,
                focusedContainerColor = SecondaryColor,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                unfocusedBorderColor = SecondaryColor,
                focusedBorderColor = SecondaryColor
            )
        )
    }
}

@Composable
@Preview
fun SearchBarItemPreview() {
    SearchBarItem (
        text = "",
        readOnly = true,
        onClick = {},
        onValueChange = {},
        onSearch = {}
    )
}

/* ---------------------------------------- */

@Composable
fun GenreItem (
    genre: Genre,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
    boxColor: Color = SecondaryColor,
    textColor: Color = Color.White
) {
    Text (
        modifier = modifier
            .background(boxColor, RoundedCornerShape(SmallSpace))
            .padding(SmallSpace)
            .clickable { onClick(genre.id) },
        text = genre.name,
        color = textColor,
    )
}

@Composable
@Preview
fun GenreItemPreview () {
    GenreItem(
        genre = Genre(0,"Action"),
        onClick = {}
    )
}

/* ---------------------------------------- */

@Composable
fun MovieItemVertical (
    modifier: Modifier = Modifier,
    movie: Movie,
    navigateToDetails: (Int, Movie) -> Unit,
    isBookmarked: Boolean,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    Column (
        modifier = modifier
            .padding(end = SmallSpace)
            .clickable { navigateToDetails(movie.id, movie) },
        horizontalAlignment = Alignment.Start
    ) {
        Box (
            modifier = Modifier
                .height(160.dp)
                .clip(RoundedCornerShape(MediumSpace))
                .border(1.dp, SecondaryColor, RoundedCornerShape(MediumSpace))
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
                icon = if (isBookmarked) R.drawable.ic_bookmark_focused else R.drawable.ic_bookmark,
                tint = if (isBookmarked) PrimaryColor else Color.White,
                onClick = { onClick() }
            )
        }
        SpacerHeight(MediumSpace)
        TextMedium(text = movie.title)
        RatingBarItem(ratingAverage = movie.voteAverage)
    }
}