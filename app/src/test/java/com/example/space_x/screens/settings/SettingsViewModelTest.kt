package com.example.space_x.screens.settings

import com.example.space_x.others.UnitSystem
import com.example.space_x.repositories.FakeSettingsRepository
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class SettingsViewModelTest {

    lateinit var viewModel: SettingsViewModel

    @Before
    fun setViewModel(){
        viewModel = SettingsViewModel(FakeSettingsRepository())
    }

    @Test
    fun setUnitSystem() {
        UnitSystem.values().forEach { unitSystem ->
            viewModel.setUnitSystem(unitSystem)
            assertThat(viewModel.unitSystem).isEqualTo(unitSystem)
        }
    }
}