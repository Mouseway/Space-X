package com.example.space_x.data.remote.dataSources

import com.example.space_x.data.remote.apiDescriptions.RocketApiDescription
import com.example.space_x.domain.rocket.Rocket
import retrofit2.HttpException

class RocketRemoteDataSource(private val rocketApiDescription: RocketApiDescription) {

    suspend fun getAllRockets(): List<Rocket> {
        val response = rocketApiDescription.getAll()
        if(response.isSuccessful && response.body() != null)
            return response.body()!!
        throw HttpException(response)
    }

    suspend fun getRocketById(id: String): Rocket {
        val response = rocketApiDescription.getById(id)
        if(response.isSuccessful && response.body() != null){
            return response.body()!!
        }
        throw HttpException(response)
    }
}