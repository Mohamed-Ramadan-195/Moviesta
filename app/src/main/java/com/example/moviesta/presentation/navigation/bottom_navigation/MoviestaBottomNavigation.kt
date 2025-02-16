package com.example.moviesta.presentation.navigation.bottom_navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesta.R
import com.example.moviesta.ui.theme.PrimaryColor
import com.example.moviesta.ui.theme.SecondaryColor

@Composable
fun MoviestaBottomNavigation (
    items: List<MoviestaBottomNavigationItem>,
    onItemSelected: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar (
        modifier = Modifier.fillMaxWidth().windowInsetsPadding(WindowInsets.navigationBars),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem (
                selected = index == onItemSelected,
                onClick = { onItemClick(index) },
                icon = {
                    Icon (
                        painter = painterResource (
                            if (index == onItemSelected) item.iconFocused
                            else item.icon
                        ),
                        contentDescription = "Bottom Navigation Icon",
                        modifier = Modifier.size(20.dp)
                    )
                },
                label = {
                    if (index == onItemSelected) {
                        Text (
                            text = item.title,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors (
                    unselectedIconColor = SecondaryColor,
                    selectedIconColor = PrimaryColor,
                    unselectedTextColor = SecondaryColor,
                    selectedTextColor = PrimaryColor,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun MoviestaBottomNavigationPreview() {
    MoviestaBottomNavigation (
        items =
            listOf (
                MoviestaBottomNavigationItem (
                    icon = R.drawable.ic_home,
                    iconFocused = R.drawable.ic_home_focused,
                    title = "Home"
                ),
                MoviestaBottomNavigationItem (
                    icon = R.drawable.ic_search,
                    iconFocused = R.drawable.ic_search,
                    title = "Search"
                ),
                MoviestaBottomNavigationItem (
                    icon = R.drawable.ic_bookmark,
                    iconFocused = R.drawable.ic_bookmark_focused,
                    title = "Bookmark"
                )
            ),
        onItemSelected = 2,
        onItemClick = {}
    )
}