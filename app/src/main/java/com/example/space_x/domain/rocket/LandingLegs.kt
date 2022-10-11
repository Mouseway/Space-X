package com.example.space_x.domain.rocket

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LandingLegs(
    val material: String?,
    val number: Int?
)