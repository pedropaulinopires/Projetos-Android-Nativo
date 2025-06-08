package com.fyra.fit.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

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

@Composable
fun FyraFitTheme(
    darkTheme: Boolean = false,//isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}