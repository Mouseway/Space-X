package com.example.space_x.domain.rocket

import com.example.space_x.others.UnitSystem
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class Length(
    val feet: Double?,
    val meters: Double?
) {
    fun getBySystem(unitSystem: UnitSystem): Double? {
        return when(unitSystem){
            UnitSystem.MetricSystem -> meters
            UnitSystem.ImperialSystem -> feet
        }
    }
}