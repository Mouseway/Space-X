package com.example.space_x.data.remote.dataSources

import com.example.space_x.data.remote.apiDescriptions.CompanyApiDescription
import com.example.space_x.domain.company.Company
import retrofit2.HttpException

class CompanyRemoteDataSource(private val companyApiDescription: CompanyApiDescription) {
    suspend fun getCompany(): Company {
        val response = companyApiDescription.getCompany()
        if(response.isSuccessful && response.body() != null){
            return response.body()!!
        }else{
            throw HttpException(response)
        }
    }
}