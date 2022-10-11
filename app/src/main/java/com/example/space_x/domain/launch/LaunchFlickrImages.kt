package com.example.space_x.domain.launch


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LaunchFlickrImages(
    @Json(name = "original")
    val original: List<String>?,
    @Json(name = "small")
    val small: List<String>?
)