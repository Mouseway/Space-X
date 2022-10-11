package com.example.space_x.data.remote.dataSources

import com.example.space_x.data.remote.responses.LaunchResponse
import com.example.space_x.data.remote.apiDescriptions.LaunchApiDescription
import retrofit2.HttpException

class LaunchRemoteDataSource(private val launchApiDescription: LaunchApiDescription) {

    suspend fun getAllByQuery(body: Map<String, Any>): LaunchResponse {
        val response = launchApiDescription.getAllByQuery(query = body)

        if(response.isSuccessful && response.body() != null) {
            return response.body()!!
        }
        throw HttpException(response)
    }

}