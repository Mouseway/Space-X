package com.example.space_x.di

import com.example.space_x.data.local.dataSources.SettingsLocalDataSource
import com.example.space_x.data.local.preferences.SettingsPreferences
import com.example.space_x.repositories.SettingsRepository
import com.example.space_x.screens.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    factory {
        SettingsPreferences(prefs = get())
    }

    factory {
        SettingsLocalDataSource(preferences = get())
    }

    factory {
        SettingsRepository(settingsDataSource = get())
    }

    viewModel {
        SettingsViewModel(settingsRepository = get<SettingsRepository>())
    }
}