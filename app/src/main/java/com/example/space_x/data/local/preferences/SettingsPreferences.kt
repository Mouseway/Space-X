package com.example.space_x.data.local.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.space_x.others.UnitSystem

class SettingsPreferences(private val prefs: SharedPreferences) {
    var unitSystem: UnitSystem
        get() = UnitSystem.fromInt(prefs.getInt(UNIT_SYSTEM_KEY, UnitSystem.MetricSystem.id))

        set(system) {
            prefs.edit { putInt(UNIT_SYSTEM_KEY, system.id)}
        }

    companion object{
        private const val UNIT_SYSTEM_KEY = "unit_system"
    }
}

