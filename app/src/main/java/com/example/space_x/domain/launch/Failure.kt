package com.example.space_x.domain.launch


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Failure(
    val altitude: Int?,
    val reason: String?,
    val time: Int?
)