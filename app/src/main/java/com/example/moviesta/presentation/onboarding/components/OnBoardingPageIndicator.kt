package com.example.moviesta.presentation.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviesta.ui.theme.PrimaryColor
import com.example.moviesta.ui.theme.SecondaryColor
import com.example.moviesta.util.Dimen

@Composable
fun OnBoardingPageIndicator (
    modifier: Modifier = Modifier,
    pageCount: Int,
    selectedPage: Int,
    selectedColor: Color = PrimaryColor,
    unSelectedColor: Color = SecondaryColor
) {
    Row (
        modifier = modifier.width(48.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(times = pageCount) { indicator ->
            Box (
                modifier = Modifier
                    .size (Dimen.SmallSpace)
                    .clip(CircleShape)
                    .background (
                        if (indicator == selectedPage) selectedColor
                        else unSelectedColor
                    )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun OnBoardingPageIndicatorPreview() {
    OnBoardingPageIndicator (
        pageCount = 3,
        selectedPage = 1
    )
}