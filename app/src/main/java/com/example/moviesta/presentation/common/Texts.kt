package com.example.moviesta.presentation.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun TextHeadline (
    text: String
) {
    Text (
        text = text,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        fontFamily = FontFamily.Serif
    )
}

@Composable
@Preview(name = "Headline")
fun TextHeadlinePreview() {
    TextHeadline("Now Playing")
}

/* ---------------------------------------- */

@Composable
fun TextMedium (
    text: String
) {
    Text (
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        color = Color.White,
        fontFamily = FontFamily.Serif
    )
}

@Composable
@Preview(name = "Medium")
fun TextMediumPreview () {
    TextMedium("Who Am I .?")
}

