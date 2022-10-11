package com.example.space_x.data.remote.responses

import com.example.space_x.domain.launch.Launch
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LaunchResponse(
    @Json(name = "docs")
    val data: List<Launch>,
    @Json(name = "hasNextPage")
    val hasNextPage: Boolean?,
    @Json(name = "hasPrevPage")
    val hasPrevPage: Boolean?,
    @Json(name = "limit")
    val limit: Int?,
    @Json(name = "nextPage")
    val nextPage: Int?,
    @Json(name = "page")
    val page: Int?,
    @Json(name = "pagingCounter")
    val pagingCounter: Int?,
    @Json(name = "prevPage")
    val prevPage: Int?,
    @Json(name = "totalDocs")
    val totalDocs: Int?,
    @Json(name = "totalPages")
    val totalPages: Int?

)