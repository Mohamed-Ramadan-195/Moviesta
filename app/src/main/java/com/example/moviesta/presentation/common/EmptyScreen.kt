package com.example.moviesta.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesta.ui.theme.PrimaryColor
import com.example.moviesta.presentation.Dimen.ExtraSmallSpace

@Composable
fun EmptyScreen (
    image: Int,
    title: String,
    body: String
) {
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image (
            painter = painterResource(image),
            contentDescription = "Placeholder",
            modifier = Modifier.size(180.dp)
        )
        Text (
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Serif,
            color = PrimaryColor
        )
        SpacerHeight(ExtraSmallSpace)
        Text (
            text = body,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Serif,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}