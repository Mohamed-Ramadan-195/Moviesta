package com.example.moviesta.presentation.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.moviesta.ui.theme.PrimaryColor

@Composable
fun TextHeadline (
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.White
) {
    Text (
        text = text,
        modifier = modifier,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = color,
        fontFamily = FontFamily.Serif
    )
}

@Composable
@Preview(name = "Headline")
fun TextHeadlinePreview() {
    TextHeadline(text = "Now Playing")
}

/* ---------------------------------------- */

@Composable
fun TextMedium (
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    fontSize: Int = 18
) {
    Text (
        text = text,
        modifier = modifier,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Medium,
        color = color,
        fontFamily = FontFamily.Serif
    )
}

@Composable
@Preview(name = "Medium")
fun TextMediumPreview () {
    TextMedium(text = "The Godfather")
}

/* ---------------------------------------- */

@Composable
fun TextAddress (
    text: String,
    modifier: Modifier = Modifier
) {
    Text (
        text = text,
        modifier = modifier,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif,
        color = PrimaryColor,
        lineHeight = 36.sp
    )
}

@Composable
@Preview
fun TextAddressPreview() {
    TextAddress(text = "Moviesta")
}