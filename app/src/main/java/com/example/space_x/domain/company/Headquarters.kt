package com.example.space_x.domain.company

import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@Serializable
@JsonClass(generateAdapter = true)
data class Headquarters(
    val address: String?,
    val city: String?,
    val state: String?
)