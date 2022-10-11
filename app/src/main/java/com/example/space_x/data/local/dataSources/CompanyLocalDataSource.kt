package com.example.space_x.data.local.dataSources

import com.example.space_x.data.local.dataStores.CompanyDataStore
import com.example.space_x.domain.company.Company
import kotlinx.coroutines.flow.Flow

class CompanyLocalDataSource(private val companyDataStore: CompanyDataStore) {

    fun getCompany(): Flow<Company> {
        return companyDataStore.getCompany()
    }

    suspend fun synchronizeCompany(company: Company){
        companyDataStore.setCompany(company = company)
    }
}