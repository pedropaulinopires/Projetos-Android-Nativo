package com.fyra.fit.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val DarkColorScheme = darkColorScheme(
    primary = VermelhoFraco,
    secondary = CinzaClaro,
    tertiary = CinzaEscuro,
    background = White
)

private val LightColorScheme = lightColorScheme(
    primary = VermelhoFraco,
    secondary = CinzaClaro,
    tertiary = CinzaEscuro,
    background = White
)

private val FyraFitTypography = Typography(
    bodyMedium = TextStyle(
        fontFamily = fontapp_poppinsFamily, fontWeight = FontWeight.Normal, fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = fontapp_poppinsFamily, fontWeight = FontWeight.Normal, fontSize = 12.sp
    ),
    displayMedium = TextStyle(
        fontFamily = fontapp_poppinsFamily, fontWeight = FontWeight.Normal, fontSize = 12.sp
    )
)

@Composable
fun FyraFitTheme(
    darkTheme: Boolean = false,//isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = FyraFitTypography,
        content = content
    )
}