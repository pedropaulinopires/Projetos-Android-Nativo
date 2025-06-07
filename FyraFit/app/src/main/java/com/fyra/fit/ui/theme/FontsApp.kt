package com.fyra.fit.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.fyra.fit.R

val fontapp_openSansFamily = FontFamily(
    Font(R.font.opensans_regular, FontWeight.Normal),
    Font(R.font.opensans_bold, FontWeight.Bold),
    Font(R.font.opensans_extrabold, FontWeight.ExtraBold),
)

val fontapp_poppinsFamily = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_extrabold, FontWeight.ExtraBold),
    Font(R.font.poppins_black, FontWeight.Black),
)

private data class ScreenInfo(
    val screenWidthDp: Int,
    val screenHeightDp: Int,
    val density: Float,
    val scaleFactor: Float
)

private val LocalScreenInfo = staticCompositionLocalOf<ScreenInfo> {
    error("No ScreenInfo provided")
}

@Composable
fun ProvideScreenInfo(content: @Composable () -> Unit) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current.density

    val baseWidth = 360f // Base de referência, como no Flutter
    val scaleFactor = configuration.screenWidthDp / baseWidth

    val screenInfo = ScreenInfo(
        screenWidthDp = configuration.screenWidthDp,
        screenHeightDp = configuration.screenHeightDp,
        density = density,
        scaleFactor = scaleFactor
    )

    CompositionLocalProvider(LocalScreenInfo provides screenInfo) {
        content()
    }
}

val Float.rsp: TextUnit
    @Composable get() = (this * LocalScreenInfo.current.scaleFactor).sp

