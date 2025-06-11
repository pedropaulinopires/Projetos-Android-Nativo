package com.fyra.fit.view.onboardingInitial

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fyra.fit.R
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingInitialScreen() {
    val viewModel = koinViewModel<OnboardingInitialViewModel>()
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { viewModel.itensOnboarding.size })

    val imageVectors = remember(viewModel.itensOnboarding) {
        viewModel.itensOnboarding.map { i -> i.idImage }
    }.map { id ->
        ImageVector.vectorResource(id = id) // aqui tudo bem, pois está dentro do escopo composable
    }

    // Função de navegação memorizada
    val onNavigate = remember {
        { nextPage: Boolean ->
            scrollNextOrPreviusPageOnboarding(
                pagerState, viewModel, nextPage, coroutineScope
            )
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
                    IconButtonArrowOnboarding(viewModel, { onNavigate(false) })
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
                            imageVectors = imageVectors
                        )

                        ContentSection(
                            modifier = Modifier
                                .height(constraints.minHeight * .5f)
                                .fillMaxWidth(),
                            maxWidth = constraints.maxWidth,
                            viewModel = viewModel,
                            onNavigate = onNavigate
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun IconButtonArrowOnboarding(
    viewModel: OnboardingInitialViewModel, onNavigateBack: () -> Unit
) {
    // Apenas este componente coleta o currentPage
    val currentPage by viewModel.currentPage.collectAsState()

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
    modifier: Modifier = Modifier, pagerState: PagerState, imageVectors: List<ImageVector>
) {
    // Este componente não precisa de nenhum estado do ViewModel
    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        HorizontalPager(
            state = pagerState,
            beyondViewportPageCount = 2,
            userScrollEnabled = false,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            Box(
                modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberVectorPainter(
                        image = imageVectors[page]
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                )
            }
        }
    }
}

@Composable
private fun ContentSection(
    modifier: Modifier = Modifier,
    maxWidth: Dp,
    viewModel: OnboardingInitialViewModel,
    onNavigate: (Boolean) -> Unit
) {
    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.width(maxWidth * .9f),
        ) {
            Spacer(modifier = Modifier.height(15.dp))

            // DotsOnboarding coleta apenas currentPage
            DotsOnboarding(viewModel = viewModel)

            Spacer(modifier = Modifier.height(15.dp))

            // TextContent coleta apenas os estados de texto
            TextContent(viewModel = viewModel, maxWidth = maxWidth)

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 20.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                // ButtonRow coleta apenas currentTextButton
                ButtonRow(
                    viewModel = viewModel,
                    onSkip = { /* Implementar ação de skip */ },
                    onNext = { onNavigate(true) })
            }
        }
    }
}

@Composable
private fun TextContent(viewModel: OnboardingInitialViewModel, maxWidth: Dp) {
    val currentTitle by viewModel.currentTitle.collectAsState()
    val currentText by viewModel.currentText.collectAsState()

    Column {
        Text(
            text = stringResource(currentTitle),
            fontFamily = fontapp_poppinsFamily,
            fontWeight = FontWeight.Black,
            color = CinzaEscuro,
            fontSize = responsiveSp(28f)

        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = stringResource(currentText),
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
    viewModel: OnboardingInitialViewModel, onSkip: () -> Unit, onNext: () -> Unit
) {
    // Apenas este componente coleta o currentTextButton
    val currentTextButton by viewModel.currentTextButton.collectAsState()
    val currentPage by viewModel.currentPage.collectAsState()
    val sizeItensOnboarding = viewModel.sizeItensOnboarding

    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextButton(onClick = onSkip) {
            if (currentPage < sizeItensOnboarding - 1) Text(
                stringResource(R.string.skip),
                color = CinzaEscuro,
                fontSize = responsiveSp(18f),
                fontWeight = FontWeight.Normal
            )
        }


        Button(
            onClick = onNext,
            shape = RoundedCornerShape(
                topStart = 10.dp, bottomStart = 10.dp, bottomEnd = 10.dp
            ),
            contentPadding = PaddingValues(vertical = 12.dp, horizontal = 20.dp),
            modifier = Modifier
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 400, easing = EaseInOut,
                    ),

                    )
                .widthIn(min = 150.dp)
        ) {
            Text(
                stringResource(currentTextButton),
                fontSize = responsiveSp(20f),
                fontWeight = FontWeight.Black
            )
        }
    }
}

@Composable
fun DotsOnboarding(viewModel: OnboardingInitialViewModel) {
    // Apenas este componente coleta currentPage
    val currentPage by viewModel.currentPage.collectAsState()

    val dotWidth = 80.dp
    val dotHeight = 5.dp
    val totalWidth = dotWidth * 3
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
            .background(color = Color(0xFFD6D5D5))
    ) {
        Box(
            modifier = Modifier
                .offset(x = animatedOffset)
                .width(dotWidth)
                .height(dotHeight)
                .clip(borderRadius)
                .background(color = VermelhoFraco)
        )
    }
}

private fun scrollNextOrPreviusPageOnboarding(
    pagerState: PagerState,
    viewModel: OnboardingInitialViewModel,
    nextPage: Boolean,
    coroutineScope: CoroutineScope
) {
    coroutineScope.launch {
        pagerState.animateScrollToPage(
            page = maxOf(0, pagerState.currentPage + (if (nextPage) 1 else -1)),
            animationSpec = tween(
                durationMillis = 400,
                easing = EaseInOut,
            )
        )
    }
    if (nextPage) viewModel.nextPage() else viewModel.previousPage()
}