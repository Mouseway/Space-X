package com.example.space_x.data.local.preferences

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.space_x.others.UnitSystem
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class SettingsPreferencesTest {

    private var preferences: SettingsPreferences? = null

    @Before
    fun init(){
        val sharedPreferences = ApplicationProvider.getApplicationContext<Context?>()?.getSharedPreferences("spacex_prefs", Context.MODE_PRIVATE)
        if(sharedPreferences != null)
            preferences = SettingsPreferences(sharedPreferences)
    }

    @Test
    fun setUnitSystem() {
        if(preferences != null){
            UnitSystem.values().forEach { system ->
                preferences!!.unitSystem = system
                assertThat(preferences!!.unitSystem).isEqualTo(system)
            }
        }
    }
}