package com.example.moviesta.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.moviesta.R
import com.example.moviesta.presentation.common.SpacerHeight
import com.example.moviesta.presentation.onboarding.model.OnBoardingModel
import com.example.moviesta.ui.theme.PrimaryBackground
import com.example.moviesta.util.Dimen

@Composable
fun OnBoardingPage (
    modifier: Modifier = Modifier,
    onBoardingModel: OnBoardingModel
) {
    Column (
        modifier = modifier.background(PrimaryBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            painter = painterResource(onBoardingModel.icon),
            contentDescription = "On-Boarding Image",
            contentScale = ContentScale.Crop
        )
        SpacerHeight(Dimen.LargeSpace)
        Text (
            modifier = Modifier.padding(Dimen.ExtraSmallSpace),
            text = onBoardingModel.title,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        SpacerHeight(Dimen.SmallSpace)
        Text (
            modifier = Modifier.padding(Dimen.ExtraSmallSpace),
            text = onBoardingModel.body,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White
        )
    }
}

@Composable
@Preview(showBackground = true)
fun OnBoardingPagePreview() {
    OnBoardingPage (
        onBoardingModel = OnBoardingModel (
            icon = R.drawable.onboarding1,
            title = "Find Your Perfect Movie Match",
            body = "Explore a vast collection of movies from all genres, curated just for you"
        )
    )
}