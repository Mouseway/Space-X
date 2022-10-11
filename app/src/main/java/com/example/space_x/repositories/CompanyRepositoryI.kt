package com.example.space_x.repositories

import com.example.space_x.domain.company.Company
import kotlinx.coroutines.flow.Flow

interface CompanyRepositoryI {
    fun getCompany(): Flow<Company>
    suspend fun fetchCompany()
}