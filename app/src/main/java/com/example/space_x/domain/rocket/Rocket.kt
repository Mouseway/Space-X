package com.example.space_x.domain.rocket

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rocket(
    val active: Boolean?,
    val boosters: Int?,
    val company: String?,
    @Json(name = "cost_per_launch")
    val costPerLaunch: Int?,
    val country: String?,
    val description: String?,
    val diameter: Length?,
    @Json(name = "first_flight")
    val firstFlight: String?,
    @Json(name = "first_stage")
    val firstStage: Stage?,
    @Json(name = "flickr_images")
    val flickrImages: List<String>?,
    val height: Length?,
    val id: String?,
    @Json(name = "landing_legs")
    val landingLegs: LandingLegs?,
    val mass: Mass?,
    val name: String?,
    @Json(name = "second_stage")
    val secondStage: Stage?,
    val stages: Int?,
    @Json(name = "success_rate_pct")
    val successRatePct: Int?,
    val type: String?,
    val wikipedia: String?
)