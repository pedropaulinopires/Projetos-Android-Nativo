package com.fyra.fit.view.onboardingInitial

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fyra.fit.R
import com.fyra.fit.model.onboardingInitial.OnboardingInitialItemModel
import com.fyra.fit.ui.theme.CinzaEscuro
import com.fyra.fit.ui.theme.CinzaMedio
import com.fyra.fit.ui.theme.VermelhoFraco
import com.fyra.fit.ui.theme.White
import com.fyra.fit.ui.theme.fontapp_poppinsFamily
import com.fyra.fit.ui.theme.responsiveSp
import com.fyra.fit.viewmodel.onboardingInitial.OnboardingInitialViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingInitialScreen() {
    val viewModel: OnboardingInitialViewModel = viewModel()
    val currentPage by viewModel.currentPage.collectAsState()
    val itensOnboarding = viewModel.itensOnboarding
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { 3 })
    val onNavigate = remember {
        { nextPage: Boolean ->
            scrollNextOrPreviusPageOnboarding(
                pagerState,
                nextPage,
                coroutineScope,
                { viewModel.nextPage() },
                { viewModel.previousPage() })
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(), topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = White,
                ),
                modifier = Modifier.padding(horizontal = 15.dp),
                title = { },
                navigationIcon = {
                    IconButtonArrowOnboarding(currentPage, { onNavigate(false) })
                },
            )
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BoxWithConstraints(
                modifier = Modifier.fillMaxSize(),
            ) {
                val constraints = this
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Column {
                        ImageSection(
                            modifier = Modifier
                                .height(constraints.minHeight * .5f)
                                .fillMaxWidth(),
                            pagerState = pagerState,
                            itensOnboarding = itensOnboarding
                        )

                        Box(
                            modifier = Modifier
                                .height(constraints.minHeight * .5f)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                modifier = Modifier.width(constraints.maxWidth * .9f),
                            ) {
                                Spacer(modifier = Modifier.height(15.dp))

                                DotsOnboarding(currentPage = currentPage)

                                Spacer(modifier = Modifier.height(15.dp))

                                TextContent(
                                    currentPage = currentPage,
                                    itensOnboarding = itensOnboarding,
                                    maxWidth = constraints.maxWidth
                                )

                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(bottom = 20.dp),
                                    contentAlignment = Alignment.BottomEnd
                                ) {
                                    ButtonRow(
                                        currentPage = currentPage,
                                        itensOnboardingSize = itensOnboarding.size,
                                        onSkip = { },
                                        onNext = { onNavigate(true) })
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}

@Composable
private fun IconButtonArrowOnboarding(
    currentPage: Int, onNavigateBack: () -> Unit
) {
    if (currentPage > 0) {
        IconButton(
            onClick = onNavigateBack, modifier = Modifier.scale(1.4f)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.arrow_back),
                contentDescription = "Arrow back",
                tint = CinzaEscuro,
                modifier = Modifier.size(48.dp)
            )
        }
    }
}

@Composable
private fun ImageSection(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    itensOnboarding: List<OnboardingInitialItemModel>
) {
    val imagesPainter = remember { itensOnboarding.map { it.idImage } }.map { i ->
        rememberVectorPainter(
            image = ImageVector.vectorResource(id = i)
        )
    }

    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        HorizontalPager(
            state = pagerState,
            beyondViewportPageCount = 2,
            userScrollEnabled = true,
            modifier = Modifier.fillMaxWidth()
        ) { page ->


            Box(
                modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = imagesPainter[page],
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

@Composable
private fun TextContent(
    currentPage: Int, itensOnboarding: List<OnboardingInitialItemModel>, maxWidth: Dp
) {
    val listTitle = remember { itensOnboarding.map { it.idTitle } }.map { i -> stringResource(i) }
    val listText = remember { itensOnboarding.map { it.idText } }.map { i -> stringResource(i) }

    Column {
        Text(
            listTitle[currentPage],
            fontFamily = fontapp_poppinsFamily,
            fontWeight = FontWeight.Black,
            color = CinzaEscuro,
            fontSize = responsiveSp(28f)

        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            listText[currentPage],
            fontFamily = fontapp_poppinsFamily,
            fontWeight = FontWeight.Normal,
            color = CinzaMedio,
            fontSize = responsiveSp(16f),
            lineHeight = 28.sp

        )
    }
}

@Composable
private fun ButtonRow(
    currentPage: Int, itensOnboardingSize: Int, onSkip: () -> Unit, onNext: () -> Unit
) {
    val stringSkip = stringResource(R.string.skip)
    val stringNext = stringResource(R.string.next)
    val stringStartTraining = stringResource(R.string.start_training)

    val isLastPage = currentPage == itensOnboardingSize - 1
    val buttonText = remember(isLastPage) {
        if (isLastPage) stringStartTraining else stringNext
    }

    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(contentAlignment = Alignment.BottomStart) {
            if (!isLastPage) {
                TextButton(onClick = onSkip) {
                    Text(
                        stringSkip,
                        color = CinzaEscuro,
                        fontSize = responsiveSp(18f),
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }

        Box(contentAlignment = Alignment.BottomEnd) {
            Button(
                onClick = onNext,
                shape = RoundedCornerShape(
                    topStart = 10.dp, bottomStart = 10.dp, bottomEnd = 10.dp
                ),
                contentPadding = PaddingValues(vertical = 12.dp, horizontal = 20.dp),
                modifier = Modifier
                    .animateContentSize()
                    .widthIn(min = 150.dp) // SEM animateContentSize()
            ) {
                Text(
                    buttonText, fontSize = responsiveSp(20f), fontWeight = FontWeight.Black
                )
            }
        }
    }
}

@Composable
private fun DotsOnboarding(currentPage: Int) {

//    val dotWidth = 80.dp
//    val dotHeight = 5.dp
//    val borderRadius = RoundedCornerShape(10.dp)
//    val totalDots = 3
//
//    val density = LocalDensity.current
//    val dotWidthPx = with(density) { dotWidth.toPx() }
//
//    val animatedOffsetPx by animateFloatAsState(
//        targetValue = dotWidthPx * currentPage,
//        animationSpec = tween(durationMillis = 400, easing = EaseInOut),
//        label = "dotBarOffset"
//    )
//
//    val animatedOffsetDp = with(density) { animatedOffsetPx.toDp() }
//
//    Box(
//        modifier = Modifier
//            .width(dotWidth * totalDots)
//            .height(dotHeight)
//            .background(color = Color(0xFFD6D5D5), shape = borderRadius)
//    ) {
//        Box(
//            modifier = Modifier
//                .offset(x = animatedOffsetDp)
//                .size(width = dotWidth, height = dotHeight)
//                .background(color = VermelhoFraco, shape = borderRadius)
//        )
//    }
}


private fun scrollNextOrPreviusPageOnboarding(
    pagerState: PagerState,
    isNextPage: Boolean,
    coroutineScope: CoroutineScope,
    nextPage: () -> Unit,
    previousPage: () -> Unit,
) {
    coroutineScope.launch {
        pagerState.animateScrollToPage(
            page = maxOf(0, pagerState.currentPage + (if (isNextPage) 1 else -1)),
            animationSpec = tween(
                durationMillis = 400,
                easing = EaseInOut,
            )
        )
    }
    if (isNextPage) nextPage() else previousPage()
}



