package com.jeton.hackednews

import android.app.Application
import com.jeton.hackednews.core.di.appModule
import com.jeton.hackednews.core.di.repositoryModule
import com.jeton.hackednews.core.di.viewModelModule
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(listOf(appModule, repositoryModule, viewModelModule))
        }
    }
}