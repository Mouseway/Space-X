package com.example.space_x.domain.rocket

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stage(
    val burn_time_sec: Int?,
    val engines: Int?,
    val fuel_amount_tons: Double?,
    val reusable: Boolean?
)