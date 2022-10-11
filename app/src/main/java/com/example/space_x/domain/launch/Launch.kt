package com.example.space_x.domain.launch


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Launch(
    @Json(name = "date_precision")
    val datePrecision: String?,
    @Json(name = "date_unix")
    val dateUnix: Int?,
    val details: String?,
    val failures: List<Failure?>?,
    @Json(name = "flight_number")
    val flightNumber: Int?,
    val id: String?,
    val name: String?,
    val net: Boolean?,
    val rocket: String?,
    val success: Boolean?,
    val upcoming: Boolean?,
    val links: LaunchLinks?
)