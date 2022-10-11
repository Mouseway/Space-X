package com.example.space_x.domain.company

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable


@Serializable
@JsonClass(generateAdapter = true)
data class Company(
    val name: String? = null,
    val founder: String? = null,
    val founded: Int? = null,
    val employees: Int? = null,
    val vehicles: Int? = null,
    val headquarters: Headquarters? = null,
    val summary: String? = null,
    @Json(name = "launch_sites")
    val launchSites: Int? = null,
    val ceo: String? = null,
    val cto: String? = null,
    val coo: String? = null,
    @Json(name = "cto_propulsion")
    val ctoPropulsion: String? = null
) {}