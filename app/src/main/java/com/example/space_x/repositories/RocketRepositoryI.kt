package com.example.space_x.repositories

import com.example.space_x.domain.rocket.Rocket

interface RocketRepositoryI {
    suspend fun getAllRockets(): List<Rocket>
    suspend fun getRocketById(id: String): Rocket
}