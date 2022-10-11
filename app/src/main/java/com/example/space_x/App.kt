package com.example.space_x

import android.app.Application
import com.example.space_x.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // declare used Android context
            androidContext(this@App)
            // declare modules
            modules(appModule + companyModule + rocketModule + settingsModule + launchModule)
        }
    }
}