package com.example.space_x.di

import com.example.space_x.data.remote.apiDescriptions.LaunchApiDescription
import com.example.space_x.data.remote.dataSources.LaunchRemoteDataSource
import com.example.space_x.repositories.LaunchRepository
import com.example.space_x.repositories.LaunchRepositoryI
import com.example.space_x.screens.launches.LaunchesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val launchModule = module {
    factory {
        get<Retrofit>().create(LaunchApiDescription::class.java)
    }

    factory {
        LaunchRemoteDataSource(launchApiDescription = get())
    }

    factory {
        LaunchRepository(dataSource = get())
    }

    viewModel {
        LaunchesViewModel(launchRepository = get<LaunchRepository>())
    }
}