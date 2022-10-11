package com.example.space_x.screens.settings

import androidx.lifecycle.ViewModel
import com.example.space_x.others.UnitSystem
import com.example.space_x.repositories.SettingsRepositoryI

open class SettingsViewModel(private val settingsRepository: SettingsRepositoryI) : ViewModel(){

    open val unitSystem: UnitSystem
            get() = settingsRepository.getUnitSystem()

    open fun setUnitSystem(unitSystem: UnitSystem) = settingsRepository.setUnitSystem(unitSystem)

}