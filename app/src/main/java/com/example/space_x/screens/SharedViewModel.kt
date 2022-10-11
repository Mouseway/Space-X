package com.example.space_x.screens

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.ViewModel

@OptIn(ExperimentalMaterial3Api::class)
open class SharedViewModel : ViewModel() {
    open val drawerState: DrawerState = DrawerState(DrawerValue.Closed)
    open var topBarOptions: List<Pair<String, () -> Unit>> = emptyList()
}