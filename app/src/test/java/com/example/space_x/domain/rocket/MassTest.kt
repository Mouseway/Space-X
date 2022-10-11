package com.example.space_x.domain.rocket

import com.example.space_x.others.UnitSystem
import com.google.common.truth.Truth
import org.junit.Test


internal class MassTest {

    @Test
    fun getBySystem() {
        val kilograms = 5.5
        val pounds = 121.3
        val mass = Mass(kilograms = kilograms, pounds = pounds)

        Truth.assertThat(mass.getBySystem(UnitSystem.MetricSystem)).isEqualTo(kilograms)
        Truth.assertThat(mass.getBySystem(UnitSystem.ImperialSystem)).isEqualTo(pounds)
    }
}