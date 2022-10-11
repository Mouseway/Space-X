package com.example.space_x.di

import android.content.Context
import com.example.space_x.screens.SharedViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
    single {
        androidContext().getSharedPreferences("spacex_prefs", Context.MODE_PRIVATE)
    }

    viewModel {
        SharedViewModel()
    }
}