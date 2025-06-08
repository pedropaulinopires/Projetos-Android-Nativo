package com.fyra.fit.translation

import android.content.Context
import android.os.Build
import androidx.annotation.StringRes
import com.fyra.fit.interfaces.ISharedPreferencesApp
import com.fyra.fit.interfaces.ITranslationManager
import com.fyra.fit.utils.enums.AppLanguage
import com.fyra.fit.utils.enums.KeysSharedPreferences
import java.util.Locale

class TranslationManager(
    private val context: Context,
    private val sharedPreferencesApp: ISharedPreferencesApp
) : ITranslationManager {


    override fun getCurrentLanguage(): AppLanguage {
        var langSelected =
            sharedPreferencesApp.getStringValue(KeysSharedPreferences.SELECTED_LANGUAGE)
        lateinit var langCode: String

        if (langSelected.isEmpty()) {
            langCode = Locale.getDefault().language
        } else {
            langCode = langSelected
        }

        return AppLanguage.fromCode(langCode)
    }

    override fun setLanguage(language: AppLanguage) {
        sharedPreferencesApp.saveStringValue(KeysSharedPreferences.SELECTED_LANGUAGE, language.code)
        updateAppLanguage(language)
    }

    override fun updateAppLanguage(language: AppLanguage) {
        val locale = Locale(language.code)
        Locale.setDefault(locale)

        val config = context.resources.configuration
        config.setLocale(locale)
    }

    // Método para obter string traduzida com parâmetros
    override fun getString(@StringRes resId: Int, vararg formatArgs: Any): String =
        context.getString(resId, *formatArgs)
}