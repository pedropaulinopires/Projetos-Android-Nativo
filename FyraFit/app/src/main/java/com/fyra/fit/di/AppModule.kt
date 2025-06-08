package com.fyra.fit.di

import com.fyra.fit.interfaces.ISharedPreferencesApp
import com.fyra.fit.interfaces.ITranslationManager
import com.fyra.fit.repository.SharedPreferencesApp
import com.fyra.fit.translation.TranslationManager
import org.koin.dsl.module

val sharedPreferencesAppModule = module {
    single<ISharedPreferencesApp> { SharedPreferencesApp(get()) }
}

val translationTextModule = module {
    single<ITranslationManager> {
        TranslationManager(
            context = get(),
            sharedPreferencesApp = get()
        )
    }
}
