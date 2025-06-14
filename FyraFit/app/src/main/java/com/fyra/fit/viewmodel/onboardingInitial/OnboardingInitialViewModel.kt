package com.fyra.fit.viewmodel.onboardingInitial

import androidx.lifecycle.ViewModel
import com.fyra.fit.R
import com.fyra.fit.model.onboardingInitial.OnboardingInitialItemModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingInitialViewModel : ViewModel() {

    // Itens do onboarding (imutáveis)
    private val _itensOnboarding = listOf(
        OnboardingInitialItemModel(
            R.drawable.onboarding_1, R.string.title_onboarding1, R.string.text_onboarding1
        ), OnboardingInitialItemModel(
            R.drawable.onboarding_2, R.string.title_onboarding2, R.string.text_onboarding2
        ), OnboardingInitialItemModel(
            R.drawable.onboarding_3, R.string.title_onboarding3, R.string.text_onboarding3
        )
    )

    val itensOnboarding: List<OnboardingInitialItemModel>
        get() = _itensOnboarding

    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage.asStateFlow()

    fun nextPage() {
        val nextIndex = _currentPage.value + 1
        if (nextIndex >= _itensOnboarding.size) return
        _currentPage.value = nextIndex
    }

    fun previousPage() {
        val prevIndex = _currentPage.value - 1
        if (prevIndex < 0) return
        _currentPage.value = prevIndex
    }
}