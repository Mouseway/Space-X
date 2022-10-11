package com.example.space_x

import android.app.Application
import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.space_x.screens.SharedViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {

        }
    }
}