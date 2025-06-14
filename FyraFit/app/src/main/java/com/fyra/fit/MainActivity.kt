package com.fyra.fit.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.fyra.fit.ui.theme.FyraFitTheme
import com.fyra.fit.view.onboardingInitial.OnboardingInitialScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        installSplashScreen()

        setContent {
            FyraFitTheme { OnboardingInitialScreen() }
        }
    }
}


