package com.fyra.fit.interfaces

import androidx.annotation.StringRes
import com.fyra.fit.utils.enums.AppLanguage

interface ITranslationManager {

    fun getCurrentLanguage(): AppLanguage

    fun setLanguage(language: AppLanguage)

    fun updateAppLanguage(language: AppLanguage)

    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String
}