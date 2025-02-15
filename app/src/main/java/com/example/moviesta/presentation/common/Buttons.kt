package com.example.moviesta.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.moviesta.R
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
        contentPadding = PaddingValues(all = Dimen.SmallSpace),
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
    onClick: () -> Unit,
    color: Color = Color.White,
    modifier: Modifier = Modifier,
    fontSize: Int = 14
) {
    TextButton (
        modifier = modifier,
        onClick = onClick,
        contentPadding = PaddingValues(all = Dimen.SmallSpace)
    ) {
        Text (
            text = text,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold,
            color = color,
            fontFamily = FontFamily.Serif
        )
    }
}

@Composable
@Preview
fun MoviestaTextButtonPreview() {
    MoviestaTextButton (
        text = "Back",
        onClick = {}
    )
}

@Composable
fun MoviestaIconButton (
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    tint: Color = Color.White
) {
    IconButton (
        modifier = modifier,
        onClick = onClick
    ) {
        Icon (
            painter = painterResource(icon),
            contentDescription = "Icon Button",
            tint = tint
        )
    }
}

@Composable
@Preview
fun MoviestaIconButtonPreview() {
    MoviestaIconButton(
        icon = R.drawable.ic_bookmark,
        onClick = {}
    )
}