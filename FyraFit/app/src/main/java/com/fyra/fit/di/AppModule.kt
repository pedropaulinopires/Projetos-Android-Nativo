package com.fyra.fit.di

import android.content.Context
import android.content.SharedPreferences
import com.fyra.fit.interfaces.ISharedPreferencesApp
import com.fyra.fit.interfaces.ITranslationManager
import com.fyra.fit.repository.SharedPreferencesApp
import com.fyra.fit.translation.TranslationManager
import com.fyra.fit.viewmodel.onboardingInitial.OnboardingInitialViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<SharedPreferences> {
        val context: Context = get()
        context.getSharedPreferences("fyra_prefs", Context.MODE_PRIVATE)
    }

    single<ISharedPreferencesApp> { SharedPreferencesApp(get()) }

    single<ITranslationManager> {
        TranslationManager(
            context = get(),
            sharedPreferencesApp = get()
        )
    }
}

val viewModelModule = module {

    viewModel<OnboardingInitialViewModel> {
        OnboardingInitialViewModel()
    }

}
