package com.example.space_x.data.local.dataSources

import com.example.space_x.data.local.preferences.SettingsPreferences
import com.example.space_x.others.UnitSystem

class SettingsLocalDataSource(private val preferences: SettingsPreferences) {

    fun getUnitSystem(): UnitSystem = preferences.unitSystem

    fun setUnitSystem(unitSystem: UnitSystem) {
        preferences.unitSystem = unitSystem
    }
}