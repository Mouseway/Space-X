package com.example.space_x.repositories

import com.example.space_x.data.remote.dataSources.RocketRemoteDataSource
import com.example.space_x.domain.rocket.Rocket

class RocketRepository(private val rocketDataSource: RocketRemoteDataSource) : RocketRepositoryI {
    override suspend fun getAllRockets(): List<Rocket> {
        return rocketDataSource.getAllRockets()
    }

    override suspend fun getRocketById(id: String): Rocket {
        return rocketDataSource.getRocketById(id)
    }
}