package com.fyra.fit.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp


@Composable
fun responsiveSp(baseSp: Float): TextUnit = (baseSp * LocalConfiguration.current.screenWidthDp / 390f).sp
