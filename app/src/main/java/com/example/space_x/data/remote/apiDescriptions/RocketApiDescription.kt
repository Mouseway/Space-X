package com.example.space_x.data.remote.apiDescriptions

import com.example.space_x.domain.rocket.Rocket
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RocketApiDescription {
    @GET("v4/rockets")
    suspend fun getAll(): Response<List<Rocket>>

    @GET("v4/rockets/{id}")
    suspend fun getById(@Path("id") rocketId: String): Response<Rocket>
}