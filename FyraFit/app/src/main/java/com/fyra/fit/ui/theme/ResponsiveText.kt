package com.fyra.fit.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

object ResponsiveText {

    // Cache para evitar recálculos desnecessários
    private val sizeCache = mutableMapOf<Pair<Float, Int>, TextUnit>()

    @Composable
    fun size(baseSize: Float): TextUnit {
        val screenWidth = LocalConfiguration.current.screenWidthDp

        // Use remember para cachear baseado na largura da tela
        return remember(baseSize, screenWidth) {
            val key = baseSize to screenWidth
            sizeCache.getOrPut(key) {
                when {
                    screenWidth < 600 -> baseSize.sp
                    screenWidth < 840 -> (baseSize * 1.2f).sp
                    else -> (baseSize * 1.4f).sp
                }
            }
        }
    }

    // Função para limpar cache se necessário (opcional)
    fun clearCache() {
        sizeCache.clear()
    }
}