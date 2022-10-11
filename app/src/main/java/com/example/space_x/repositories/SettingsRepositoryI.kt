package com.example.space_x.repositories

import com.example.space_x.others.UnitSystem

interface SettingsRepositoryI {
    fun getUnitSystem(): UnitSystem
    fun setUnitSystem(unitSystem: UnitSystem)
}