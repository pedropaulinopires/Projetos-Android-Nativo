package com.fyra.fit.model.onboardingInitial

data class OnboardingInitialItemModel(
    private val _idImage: Int,
    private val _title: Int,
    private val _text: Int,
) {
    val idImage: Int get() = _idImage
    val idTitle: Int get() = _title
    val idText: Int get() = _text
}