package com.fyra.fit

import android.app.Application
import com.fyra.fit.di.sharedPreferencesAppModule
import com.fyra.fit.di.translationTextModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class FyraFitApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@FyraFitApplication)
            modules(
                sharedPreferencesAppModule,
                translationTextModule
            )
        }
    }
}