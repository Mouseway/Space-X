package com.example.space_x.repositories

import com.example.space_x.others.UnitSystem

class FakeSettingsRepository : SettingsRepositoryI {

    private var unitSystem = UnitSystem.ImperialSystem

    override fun getUnitSystem(): UnitSystem = unitSystem

    override fun setUnitSystem(unitSystem: UnitSystem) {
        this.unitSystem = unitSystem
    }
}