package com.example.space_x.di

import com.example.space_x.data.remote.apiDescriptions.RocketApiDescription
import com.example.space_x.data.remote.dataSources.RocketRemoteDataSource
import com.example.space_x.repositories.RocketRepository
import com.example.space_x.repositories.RocketRepositoryI
import com.example.space_x.repositories.SettingsRepository
import com.example.space_x.screens.rocketDetail.RocketDetailViewModel
import com.example.space_x.screens.rockets.RocketsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val rocketModule = module {

    factory {
        get<Retrofit>().create(RocketApiDescription::class.java)
    }

    factory {
        RocketRemoteDataSource(rocketApiDescription = get())
    }

    factory {
        RocketRepository(rocketDataSource = get())
    }

    viewModel {
        RocketsViewModel(rocketRepository = get<RocketRepository>())
    }

    viewModel {
            params ->
        RocketDetailViewModel(rocketRepository = get<RocketRepository>(), rocketId = params.get(), settingsRepository = get<SettingsRepository>())
    }
}