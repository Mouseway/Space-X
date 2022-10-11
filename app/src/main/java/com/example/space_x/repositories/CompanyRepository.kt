package com.example.space_x.repositories

import com.example.space_x.data.local.dataSources.CompanyLocalDataSource
import com.example.space_x.data.remote.dataSources.CompanyRemoteDataSource
import com.example.space_x.domain.company.Company
import kotlinx.coroutines.flow.Flow

class CompanyRepository(
    private val remoteDataSource: CompanyRemoteDataSource,
    private val localDataSource: CompanyLocalDataSource
) : CompanyRepositoryI {
    override fun getCompany(): Flow<Company>  = localDataSource.getCompany()

    // Loads the company data from remote source and synchronize them with the local company data
    override suspend fun fetchCompany(){
        val apiCompany = remoteDataSource.getCompany()
        localDataSource.synchronizeCompany(apiCompany)
    }
}