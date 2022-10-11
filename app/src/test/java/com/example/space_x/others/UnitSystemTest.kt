package com.example.space_x.others

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UnitSystemTest{

    @Test
    fun `get system from Int`(){
        UnitSystem.values().forEach { system ->
            assertThat(UnitSystem.fromInt(system.id)).isEqualTo(system)
        }
    }
}