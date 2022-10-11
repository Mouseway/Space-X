package com.example.space_x.screens.settings

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.space_x.R
import com.example.space_x.screens.SharedViewModel
import com.example.space_x.others.AppBar
import com.example.space_x.others.composable.Title
import com.example.space_x.others.UnitSystem
import com.example.space_x.others.composable.RadiobuttonGroup
import org.koin.androidx.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(sharedViewModel: SharedViewModel) {

    val viewModel by viewModel<SettingsViewModel>()

    val stateAtBeginning = viewModel.unitSystem
    val radioOptions = listOf(UnitSystem.MetricSystem, UnitSystem.ImperialSystem)
    val selectedOption = remember { mutableStateOf(stateAtBeginning)}
    val saveButtonEnabled = remember { mutableStateOf(false)}

    Scaffold(topBar ={
        AppBar(
            title = stringResource(id = R.string.space_x),
            drawerState = sharedViewModel.drawerState,
            options = sharedViewModel.topBarOptions
        )
    }
    ) { padding ->
        Box(Modifier.padding(padding)) {

            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
            ) {
                Title(stringResource(id = R.string.settings_title))

                Spacer(modifier = Modifier.height(20.dp))

                // Radiobutton group with unit systems
                RadiobuttonGroup(
                    title = stringResource(R.string.unit_system),
                    radioOptions = radioOptions.map { stringResource(id = it.titleId) },
                    selectedOption = radioOptions.indexOf(selectedOption.value),
                    onOptionSelected = { systemIndex ->
                        selectedOption.value = radioOptions[systemIndex]
                        saveButtonEnabled.value = radioOptions[systemIndex] != stateAtBeginning
                    }
                )

                // Button for saving the setting
                // The button is enabled when there is a change to save.
                Box(modifier = Modifier.fillMaxSize()) {
                    Button(
                        onClick = {
                            viewModel.setUnitSystem(selectedOption.value)
                            saveButtonEnabled.value = false
                        },
                        enabled = saveButtonEnabled.value,
                        modifier = Modifier.align(Alignment.BottomCenter)
                    ) {
                        Text(text = stringResource(R.string.save))
                    }
                }
            }
        }
    }
}


