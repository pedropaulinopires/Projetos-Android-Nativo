package com.fyra.fit.view.onboardingInitial

import android.util.Size
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.fyra.fit.R
import com.fyra.fit.ui.theme.CinzaClaro
import com.fyra.fit.ui.theme.CinzaEscuro
import com.fyra.fit.ui.theme.CinzaMedio
import com.fyra.fit.ui.theme.ResponsiveText
import com.fyra.fit.ui.theme.VermelhoForte
import com.fyra.fit.ui.theme.VermelhoFraco
import com.fyra.fit.ui.theme.White
import com.fyra.fit.ui.theme.fontapp_poppinsFamily
import com.fyra.fit.viewmodel.onboardingInitial.OnboardingInitialUiSate
import com.fyra.fit.viewmodel.onboardingInitial.OnboardingInitialViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingInitialScreen() {
    val viewModel = koinViewModel<OnboardingInitialViewModel>()
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { viewModel.itensOnboarding.size })
    val onboardingUiSate = viewModel.onboardingUiSate.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(), topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = White,
                ),
                modifier = Modifier.padding(horizontal = 15.dp),
                title = { },
                navigationIcon = {
                    if (onboardingUiSate.value.currentPage > 0) IconButton(
                        onClick = {
                            coroutineScope.launch {
                                val targetPage =
                                    maxOf(0, pagerState.currentPage - 1)
                                viewModel.previousPage()
                                pagerState.animateScrollToPage(
                                    page = targetPage, animationSpec = tween(
                                        durationMillis = 400,
                                        easing = EaseInOut,
                                    )
                                )
                            }
                        }, modifier = Modifier.scale(1.4f)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.arrow_back),
                            contentDescription = "Arrow back",
                            tint = CinzaEscuro,
                            modifier = Modifier.size(48.dp)
                        )
                    }
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
                val constraints = this;
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Column(
                    ) {
                        Box(
                            Modifier.height(constraints.minHeight * .5f)
                        ) {
                            HorizontalPager(
                                state = pagerState,
                                beyondViewportPageCount = 2,
                                userScrollEnabled = false,
                                modifier = Modifier.fillMaxWidth()
                            ) { page ->
                                Image(
                                    painter = rememberVectorPainter(
                                        image = ImageVector.vectorResource(
                                            id = viewModel.itensOnboarding[page].idImage
                                        )
                                    ), contentDescription = null, contentScale = ContentScale.Fit
                                )
                            }
                        }
                        Box(
                            Modifier
                                .height(constraints.minHeight * .5f)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                modifier = Modifier.width(constraints.maxWidth * .9f),
                            ) {
                                Spacer(modifier = Modifier.height(15.dp))
                                DotsOnboarding(
                                    currentPage = onboardingUiSate.value.currentPage,
                                    lengthDots = viewModel.itensOnboarding.size
                                )
                                Spacer(modifier = Modifier.height(15.dp))
                                Text(
                                    stringResource(viewModel.itensOnboarding[onboardingUiSate.value.currentPage].idTitle),
                                    fontFamily = fontapp_poppinsFamily,
                                    fontWeight = FontWeight.Black,
                                    color = CinzaEscuro,
                                    fontSize = ResponsiveText.size(28f)
                                )
                                Spacer(modifier = Modifier.height(14.dp))
                                Text(
                                    stringResource(viewModel.itensOnboarding[onboardingUiSate.value.currentPage].idText),
                                    fontFamily = fontapp_poppinsFamily,
                                    fontWeight = FontWeight.Normal,
                                    color = CinzaMedio,
                                    lineHeight = 1.8.em,
                                    fontSize = ResponsiveText.size(16f)
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(bottom = 20.dp),
                                    contentAlignment = Alignment.BottomEnd
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        TextButton(onClick = {}) {
                                            Text("Pular")
                                        }
                                        Button(onClick = {
                                            coroutineScope.launch {
                                                val targetPage =
                                                    maxOf(0, pagerState.currentPage + 1)
                                                viewModel.nextPage()
                                                pagerState.animateScrollToPage(
                                                    page = targetPage, animationSpec = tween(
                                                        durationMillis = 400,
                                                        easing = EaseInOut,
                                                    )
                                                )
                                            }
                                        }) {
                                            Text("Próximo")
                                        }
                                    }
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
fun DotsOnboarding(lengthDots: Int, currentPage: Int) {
    val dotWidth = 80.dp
    val dotHeight = 5.dp
    val totalWidth = dotWidth * lengthDots
    val borderRadius = RoundedCornerShape(10.dp)

    val animatedOffset by animateDpAsState(
        targetValue = dotWidth * currentPage,
        animationSpec = tween(durationMillis = 400, easing = EaseInOut),
        label = "dotBarOffset"
    )

    Box(
        modifier = Modifier
            .width(totalWidth)
            .height(dotHeight)
            .clip(borderRadius)
            .background(color = Color(0xFFD6D5D5)) // cinza claro
    ) {
        Box(
            modifier = Modifier
                .offset(x = animatedOffset)
                .width(dotWidth)
                .height(dotHeight)
                .clip(borderRadius)
                .background(color = VermelhoFraco) // sua cor vermelha clara
        )
    }
}



////@Composable
////private fun NavigationButtons(
////    pagerState: androidx.compose.foundation.pager.PagerState,
////    coroutineScope: kotlinx.coroutines.CoroutineScope,
////    viewModel: OnboardingInitialViewModel
////) {
////    Row(
////        modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)
////    ) {
////        Button(
////            onClick = {
////                coroutineScope.launch {
////                    val targetPage = maxOf(0, pagerState.currentPage - 1)
////                    pagerState.animateScrollToPage(
////                        page = targetPage, animationSpec = tween(
////                            durationMillis = 400,
////                            easing = EaseInOut,
////                        )
////                    )
////                    viewModel.previusPage()
////                }
////            }, enabled = pagerState.currentPage > 0
////        ) {
////            Text("Anterior")
////        }
////
////        Button(
////            onClick = {
////                coroutineScope.launch {
////                    val targetPage = minOf(pagerState.pageCount - 1, pagerState.currentPage + 1)
////                    pagerState.animateScrollToPage(
////                        page = targetPage,
////
////                        animationSpec = tween(
////                            durationMillis = 400,
////                            easing = EaseInOut,
////                        )
////                    )
////                    viewModel.nextPage()
////                }
////            }, enabled = pagerState.currentPage < pagerState.pageCount - 1
////        ) {
////            Text("Próximo")
////        }
////    }
////}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Preview
//@Composable
//private fun TopBarOnboarding() {
//    TopAppBar(
//        colors = TopAppBarDefaults.topAppBarColors(
//            containerColor = White,
//        ),
//        title = { },
//        navigationIcon = {
//            IconButton(
//                onClick = { /* do something */ }, modifier = Modifier.width(80.dp)
//            ) {
//                Icon(
//                    imageVector = ImageVector.vectorResource(id = R.drawable.arrow_back),
//                    contentDescription = "Localized description",
//                )
//            }
//        },
//    )
//}