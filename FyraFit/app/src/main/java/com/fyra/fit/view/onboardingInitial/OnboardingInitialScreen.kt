package com.fyra.fit.view.onboardingInitial

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.fyra.fit.R
import com.fyra.fit.model.onboardingInitial.OnboardingInitialItemModel
import com.fyra.fit.ui.theme.responsiveSp
import com.fyra.fit.viewmodel.onboardingInitial.OnboardingInitialViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingInitialScreen(/*viewModel: OnboardingInitialViewModel = koinViewModel()*/) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()

    Scaffold(modifier = Modifier) { innerPadding ->
        BoxWithConstraints {
            // Memoizar os cálculos de dimensões
            val dimensions = remember(this.maxWidth, this.maxHeight) {
                OnboardingDimensions(
                    widthMaxUsable = (maxWidth.value * 0.9f).dp,
                    imageHeight = (maxHeight.value * 0.5f).dp
                )
            }

            Column(modifier = Modifier.padding(innerPadding)) {
                ImageSection(pagerState, dimensions)
                ContentSection(pagerState, coroutineScope)
            }
        }
    }
}

// Data class para memoizar dimensões
private data class OnboardingDimensions(
    val widthMaxUsable: Dp, val imageHeight: Dp
)

@Composable
private fun ImageSection(pagerState: PagerState, dimensions: OnboardingDimensions) {
    val painters = remember {
        listOf(
            R.drawable.onboarding_1, R.drawable.onboarding_2, R.drawable.onboarding_3
        )
    }.map { painterResource(id = it) }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensions.imageHeight),
        beyondViewportPageCount = 3,
        userScrollEnabled = true
    ) { page ->
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painters[page],
                contentDescription = "Onboarding image ${page + 1}",
                contentScale = ContentScale.Crop,
                modifier = Modifier.width(dimensions.widthMaxUsable),
            )
        }
    }
}

@Composable
private fun ContentSection(pagerState: PagerState, coroutineScope: CoroutineScope) {

    Spacer(Modifier.height(30.dp))

    val nextText = stringResource(R.string.next)
    val startTrainingText = stringResource(R.string.start_training)

    val buttonText by remember(nextText, startTrainingText) {
        derivedStateOf {
            if (pagerState.currentPage >= 2) startTrainingText else nextText
        }
    }

    Button(
        onClick = { scrollToNextPage(pagerState, coroutineScope) },
        shape = RoundedCornerShape(
            topStart = 10.dp, bottomStart = 10.dp, bottomEnd = 10.dp
        ),
        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 20.dp),
        modifier = Modifier.widthIn(min = 150.dp).animateContentSize()
    ) {
        Text(
            buttonText, fontSize = responsiveSp(20f), fontWeight = FontWeight.Black
        )
    }
}

private fun scrollToNextPage(
    pagerState: PagerState, coroutineScope: CoroutineScope
) {
    if (pagerState.isScrollInProgress) return
    val nextPage = (pagerState.currentPage + 1).coerceAtMost(2)
    if (pagerState.currentPage >= 2) return

    coroutineScope.launch {
        pagerState.animateScrollToPage(
            page = nextPage, animationSpec = tween(
                durationMillis = 400,
                easing = EaseInOut,
            )
        )
    }
}

