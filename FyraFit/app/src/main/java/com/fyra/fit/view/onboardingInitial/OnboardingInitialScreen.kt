package com.fyra.fit.view.onboardingInitial

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.EaseInOutSine
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.sharp.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.fyra.fit.R
import com.fyra.fit.ui.theme.White
import com.fyra.fit.viewmodel.onboardingInitial.OnboardingInitialViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnboardingInitialScreen() {
    val viewModel = koinViewModel<OnboardingInitialViewModel>()
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { viewModel.itensOnboarding.size })

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarOnboarding() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HorizontalPager(
                state = pagerState,
                beyondViewportPageCount = 2,
                userScrollEnabled = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) { page ->
                OptimizedImage(
                    imageResId = viewModel.itensOnboarding[page]["image"] as Int,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            NavigationButtons(
                pagerState = pagerState,
                coroutineScope = coroutineScope
            )
        }
    }
}


@Composable
private fun OptimizedImage(
    imageResId: Int,
    modifier: Modifier = Modifier
) {
//    AsyncImage(
//        model = ImageRequest.Builder(LocalContext.current)
//            .data(imageResId)
//            .size(Size.ORIGINAL)
//            .crossfade(false)
//            .build(),
//        contentDescription = null,
//        modifier = modifier,
//        contentScale = ContentScale.Fit
//    )
    Image(
        imageVector = ImageVector.vectorResource(id = imageResId),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Fit
    )
}

@Composable
private fun NavigationButtons(
    pagerState: androidx.compose.foundation.pager.PagerState,
    coroutineScope: kotlinx.coroutines.CoroutineScope
) {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = {
                coroutineScope.launch {
                    val targetPage = maxOf(0, pagerState.currentPage - 1)
                    pagerState.animateScrollToPage(
                        page = targetPage,
                        animationSpec = tween(
                            durationMillis = 400,
                            easing = EaseInOut,
                        )
                    )
                }
            },
            enabled = pagerState.currentPage > 0
        ) {
            Text("Anterior")
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    val targetPage = minOf(pagerState.pageCount - 1, pagerState.currentPage + 1)
                    pagerState.animateScrollToPage(
                        page = targetPage,

                        animationSpec = tween(
                            durationMillis = 400,
                            easing = EaseInOut,
                        )
                    )
                }
            },
            enabled = pagerState.currentPage < pagerState.pageCount - 1
        ) {
            Text("Próximo")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun TopBarOnboarding() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = White,
        ),
        title = { },
        navigationIcon = {
            IconButton(
                onClick = { /* do something */ },
                modifier = Modifier.width(80.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.arrow_back_ios_51dp_000000_fill0_wght100_grad0_opsz48),
                    contentDescription = "Localized description",
                )
            }
        },
    )
}