package com.example.space_x.data.remote.apiDescriptions

import com.example.space_x.data.remote.responses.LaunchResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LaunchApiDescription {
    @JvmSuppressWildcards
    @POST("v5/launches/query")
    suspend fun getAllByQuery( @Body query: Map<String, Any>): Response<LaunchResponse>
}