package com.fyra.fit.viewmodel.onboardingInitial

import androidx.lifecycle.ViewModel
import com.fyra.fit.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingInitialViewModel: ViewModel() {

    private val _currentPage = MutableStateFlow(0)
    val currentPage = _currentPage.asStateFlow()

    private val _itensOnboarding: List<Map<String, Any>> = listOf(
        mapOf(
            "image" to R.drawable.onboarding_1,
        ),
        mapOf(
            "image" to R.drawable.onboarding_2,
        ),
        mapOf(
            "image" to R.drawable.onboarding_3,
        )
    )


    val itensOnboarding: List<Map<String, Any>>
        get() = _itensOnboarding

    fun nextPage() {
        if (_currentPage.value == (_itensOnboarding.size - 1)) return
        ++_currentPage.value
    }

    fun previusPage() {
        if (_currentPage.value == 0) return
        --_currentPage.value
    }

}