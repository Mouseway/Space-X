package com.example.space_x.screens.settings

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.space_x.others.UnitSystem
import com.example.space_x.screens.SharedViewModel
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.compose.get
import org.koin.androidx.compose.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class SettingsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    lateinit var settingsViewModel: SettingsViewModel

    @OptIn(ExperimentalMaterial3Api::class)
    @Before
    fun setup(){

        settingsViewModel = mock(SettingsViewModel::class.java)
        val sharedViewModel = mock(SharedViewModel::class.java)
        `when`(sharedViewModel.topBarOptions).thenReturn(emptyList())
        `when`(sharedViewModel.drawerState).thenReturn(DrawerState(DrawerValue.Closed))
        `when`(settingsViewModel.unitSystem).thenReturn(UnitSystem.MetricSystem).thenReturn(UnitSystem.ImperialSystem)

        loadKoinModules(module{
            viewModel {
                settingsViewModel
            }
            viewModel {
                sharedViewModel
            }
        })
    }
    
    @Test
    fun withoutChangeSaveButtonDisabled(){
        composeTestRule.setContent {
            SettingsScreen(sharedViewModel = get())
        }
    }

    @After
    fun cleanUp() {
        stopKoin()
    }

}