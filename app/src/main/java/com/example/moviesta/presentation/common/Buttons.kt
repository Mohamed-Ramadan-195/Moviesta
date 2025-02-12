package com.example.moviesta.presentation.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.moviesta.ui.theme.PrimaryColor
import com.example.moviesta.util.Dimen

@Composable
fun MoviestaButton (
    text: String,
    onClick: () -> Unit
) {
    Button (
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(Dimen.ExtraLargeSpace),
        contentPadding = PaddingValues(all = Dimen.MediumSpace),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryColor,
            contentColor = Color.Black
        )
    ) {
        Text (
            text = text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MoviestaButtonPreview() {
    MoviestaButton (
        text = "Continue",
        onClick = {}
    )
}

@Composable
fun MoviestaTextButton (
    text: String,
    onClick: () -> Unit
) {
    TextButton (
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        contentPadding = PaddingValues(all = Dimen.SmallSpace)
    ) {
        Text (
            text = text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontFamily = FontFamily.Serif
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MoviestaTextButtonPreview() {
    MoviestaTextButton (
        text = "Back",
        onClick = {}
    )
}