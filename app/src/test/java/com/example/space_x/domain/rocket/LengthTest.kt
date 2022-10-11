package com.example.space_x.domain.rocket

import com.example.space_x.others.UnitSystem
import com.google.common.truth.Truth.assertThat
import org.junit.Test


internal class LengthTest {

    @Test
    fun getBySystem() {
        val meters = 3.658
        val feet = 12.0
        val length = Length(feet = feet, meters = meters)

        assertThat(length.getBySystem(UnitSystem.MetricSystem)).isEqualTo(meters)
        assertThat(length.getBySystem(UnitSystem.ImperialSystem)).isEqualTo(feet)
    }
}