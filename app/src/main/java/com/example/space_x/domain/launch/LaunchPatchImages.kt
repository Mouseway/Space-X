package com.example.space_x.domain.launch


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LaunchPatchImages(
    @Json(name = "large")
    val large: String?,
    @Json(name = "small")
    val small: String?
)