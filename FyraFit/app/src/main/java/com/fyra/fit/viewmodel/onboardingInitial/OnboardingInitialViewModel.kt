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
            R.drawable.onboarding_1, R.string.title_onboarding2, R.string.text_onboarding2
        ), OnboardingInitialItemModel(
            R.drawable.onboarding_1, R.string.title_onboarding3, R.string.text_onboarding3
        )
    )

    val itensOnboarding: List<OnboardingInitialItemModel>
        get() = _itensOnboarding

    val sizeItensOnboarding: Int
        get() = _itensOnboarding.size

    // Estados individuais
    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage.asStateFlow()

    private val _currentTextButton = MutableStateFlow(R.string.next)
    val currentTextButton: StateFlow<Int> = _currentTextButton.asStateFlow()

    private val _currentTitle = MutableStateFlow(_itensOnboarding[0].idTitle)
    val currentTitle: StateFlow<Int> = _currentTitle.asStateFlow()

    private val _currentText = MutableStateFlow(_itensOnboarding[0].idText)
    val currentText: StateFlow<Int> = _currentText.asStateFlow()

    fun nextPage() {
        val nextIndex = _currentPage.value + 1
        if (nextIndex >= sizeItensOnboarding) return

        updateState(nextIndex)
    }

    fun previousPage() {
        val prevIndex = _currentPage.value - 1
        if (prevIndex < 0) return

        updateState(prevIndex)
    }

    private fun updateState(newPageIndex: Int) {
        _currentPage.value = newPageIndex
        _currentTitle.value = _itensOnboarding[newPageIndex].idTitle
        _currentText.value = _itensOnboarding[newPageIndex].idText

        if (newPageIndex == sizeItensOnboarding - 1) _currentTextButton.value =
            R.string.start_training
        else _currentTextButton.value = R.string.next
    }
}