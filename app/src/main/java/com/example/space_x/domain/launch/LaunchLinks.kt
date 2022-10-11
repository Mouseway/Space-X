package com.example.space_x.domain.launch


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LaunchLinks(
    @Json(name = "flickr")
    val flickrImages: LaunchFlickrImages?,
    @Json(name = "patch")
    val patchImages: LaunchPatchImages?,
    @Json(name = "wikipedia")
    val wikipedia: String?
)