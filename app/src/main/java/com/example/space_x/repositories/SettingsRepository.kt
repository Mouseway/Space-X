package com.example.space_x.repositories

import com.example.space_x.data.local.dataSources.SettingsLocalDataSource
import com.example.space_x.others.UnitSystem

open class SettingsRepository(private val settingsDataSource: SettingsLocalDataSource) : SettingsRepositoryI {

   override fun getUnitSystem(): UnitSystem {
       return settingsDataSource.getUnitSystem()
   }

   override fun setUnitSystem(unitSystem: UnitSystem) {
       settingsDataSource.setUnitSystem(unitSystem)
   }
}