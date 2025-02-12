package com.example.moviesta.presentation.onboarding.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviesta.presentation.common.MoviestaButton
import com.example.moviesta.presentation.common.MoviestaTextButton
import com.example.moviesta.presentation.common.SpacerHeight
import com.example.moviesta.presentation.onboarding.components.OnBoardingPage
import com.example.moviesta.presentation.onboarding.components.OnBoardingPageIndicator
import com.example.moviesta.presentation.onboarding.model.onBoardingModelData
import com.example.moviesta.ui.theme.PrimaryBackground
import com.example.moviesta.util.Constant.EMPTY_STRING
import com.example.moviesta.util.Dimen
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen (
    onBoardingEvent: (OnBoardingEvent) -> Unit
) {
    Column (
        modifier = Modifier.fillMaxSize().background(PrimaryBackground)
    ) {
        // Handle States For Pages ( pager & button )
        val pagerState = rememberPagerState(initialPage = 0) { onBoardingModelData.size }
        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("Continue", "")
                    1 -> listOf("Continue", "Back")
                    2 -> listOf("Get Started", "Back")
                    else -> listOf("", "")
                }
            }
        }

        // UI
        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(onBoardingModel = onBoardingModelData[index])
        }
        SpacerHeight(Dimen.MediumSpace)
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimen.LargeSpace)
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OnBoardingPageIndicator (
                modifier = Modifier.width(48.dp),
                pageCount = onBoardingModelData.size,
                selectedPage = pagerState.currentPage
            )
            SpacerHeight(Dimen.MediumSpace)
            val scope = rememberCoroutineScope()
            if (buttonState.value[0].isNotEmpty()) {
                MoviestaButton (
                    text = buttonState.value[0],
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage == 2) {
                                onBoardingEvent(OnBoardingEvent.SaveAppEntry)
                            } else {
                                pagerState.animateScrollToPage (
                                    page = pagerState.currentPage + 1
                                )
                            }
                        }
                    }
                )
                MoviestaTextButton (
                    text = buttonState.value[1],
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage (
                                page = pagerState.currentPage - 1
                            )
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}

@Composable
@Preview(showBackground = true)
fun OnBoardingScreenPreview() {
    OnBoardingScreen(onBoardingEvent = {})
}