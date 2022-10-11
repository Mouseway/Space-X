package com.example.space_x.data.remote.apiDescriptions

import com.example.space_x.domain.company.Company
import retrofit2.Response
import retrofit2.http.GET

interface CompanyApiDescription {
    @GET("v4/company")
    suspend fun getCompany(): Response<Company>
}