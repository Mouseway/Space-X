package com.example.space_x.di

import com.example.space_x.data.local.dataSources.CompanyLocalDataSource
import com.example.space_x.data.local.dataStores.CompanyDataStore
import com.example.space_x.data.remote.apiDescriptions.CompanyApiDescription
import com.example.space_x.data.remote.dataSources.CompanyRemoteDataSource
import com.example.space_x.repositories.CompanyRepository
import com.example.space_x.repositories.LaunchRepository
import com.example.space_x.repositories.LaunchRepositoryI
import com.example.space_x.screens.company.CompanyViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val companyModule = module {

    factory {
        get<Retrofit>().create(CompanyApiDescription::class.java)
    }

    single {
        CompanyDataStore(androidContext())
    }

    factory {
        CompanyRemoteDataSource(companyApiDescription = get())
    }

    factory {
        CompanyLocalDataSource(companyDataStore = get())
    }

    factory {
        CompanyRepository(remoteDataSource = get(), localDataSource = get())
    }

    viewModel {
        CompanyViewModel(companyRepository = get<CompanyRepository>())
    }

}