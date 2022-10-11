package com.example.space_x.others

import com.example.space_x.R


enum class UnitSystem(val id: Int, val titleId: Int, val lengthUnitId:  Int, val massUnitId: Int) {
    ImperialSystem(titleId = R.string.imperial_system,
        id = 0,
        lengthUnitId = R.string.ft,
        massUnitId = R.string.lb
    ),
    MetricSystem(titleId = R.string.metric_system,
        id = 1,
        lengthUnitId = R.string.m,
        massUnitId = R.string.kg
    );

    companion object {
        fun fromInt(value: Int) = values().first { it.id == value }
    }
}