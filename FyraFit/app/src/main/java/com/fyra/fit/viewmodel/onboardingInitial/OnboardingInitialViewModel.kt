package com.fyra.fit.viewmodel.onboardingInitial

import androidx.lifecycle.ViewModel
import com.fyra.fit.R
import com.fyra.fit.model.onboardingInitial.OnboardingInitialItemModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingInitialViewModel : ViewModel() {

    private val _onboardingUiSate = MutableStateFlow(
        OnboardingInitialUiSate(
            currentPage = 0, textButton = "TESTE"
        )
    )
    val onboardingUiSate = _onboardingUiSate.asStateFlow()

    private val _itensOnboarding: List<OnboardingInitialItemModel> = listOf(
        OnboardingInitialItemModel(
            R.drawable.onboarding_1,
            R.string.title_onboarding1,
            R.string.text_onboarding1
        ), OnboardingInitialItemModel(
            R.drawable.onboarding_2,
            R.string.title_onboarding2,
            R.string.text_onboarding2
        ), OnboardingInitialItemModel(
            R.drawable.onboarding_3,
            R.string.title_onboarding3,
            R.string.text_onboarding3
        )
    )

    val itensOnboarding: List<OnboardingInitialItemModel>
        get() = _itensOnboarding

    fun nextPage() {
        val nextPage = _onboardingUiSate.value.currentPage + 1
        if (nextPage < itensOnboarding.size) {
            _onboardingUiSate.value = _onboardingUiSate.value.copy(currentPage = nextPage)
        }
    }

    fun previousPage() {
        val previousPage = _onboardingUiSate.value.currentPage - 1
        if (previousPage >= 0) {
            _onboardingUiSate.value = _onboardingUiSate.value.copy(currentPage = previousPage)
        }
    }
}

data class OnboardingInitialUiSate(
    var currentPage: Int,
    var textButton: String,
)

