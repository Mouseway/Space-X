package com.example.space_x.domain.rocket

import com.example.space_x.others.UnitSystem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Mass(
    @Json(name = "kg")
    val kilograms: Double?,
    @Json(name = "lb")
    val pounds: Double?
){
    fun getBySystem(unitSystem: UnitSystem): Double? {
        return when(unitSystem){
            UnitSystem.MetricSystem -> kilograms
            UnitSystem.ImperialSystem -> pounds
        }
    }
}