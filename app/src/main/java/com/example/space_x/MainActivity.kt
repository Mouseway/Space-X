package com.example.space_x

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Rocket
import androidx.compose.material.icons.rounded.RocketLaunch
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.example.space_x.navigation.Navigation
import com.example.space_x.navigation.NavigationScreens
import com.example.space_x.screens.SharedViewModel
import com.example.space_x.ui.theme.SpaceXTheme
import com.example.space_x.others.composable.DrawerContent
import com.example.space_x.others.composable.DrawerItem
import kotlinx.coroutines.launch
import org.koin.androidx.compose.viewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceXTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    val scope = rememberCoroutineScope()
    val navHostController = rememberNavController()
    val selectedDrawerIndex = remember { mutableStateOf(0) }

    val viewModel by viewModel<SharedViewModel>()

    // options in top bar menu
    viewModel.topBarOptions = listOf(
        // settings
       Pair(stringResource(R.string.settings_title)){
           navHostController.navigate(NavigationScreens.SettingsScreen.route)
       }
    )

    val drawerState = viewModel.drawerState

    // close drawer a navigate to screen
    val onDrawerItemClick = { index: Int, navigationRoute: String ->
        scope.launch {
            drawerState.close()
        }
        navHostController.navigate(navigationRoute)
        selectedDrawerIndex.value = index
    }

    // List of links in drawer
    val drawerItems = listOf(
        // About company
        DrawerItem(stringResource(R.string.about_company_title), icon = Icons.Rounded.Info){ index ->
            onDrawerItemClick(index, NavigationScreens.CompanyScreen.route)
        },
        // Rockets screen
        DrawerItem(stringResource(R.string.rockets_title), Icons.Rounded.Rocket){ index ->
            onDrawerItemClick(index, NavigationScreens.RocketsScreen.route)
        },
        // Launches screen
        DrawerItem(stringResource(R.string.launches_title), Icons.Rounded.RocketLaunch){ index ->
            onDrawerItemClick(index, NavigationScreens.LaunchesScreen.route)
        }
    )

   ModalNavigationDrawer(
       drawerContent = {
            DrawerContent(items = drawerItems, selectedIndex = selectedDrawerIndex.value)
       },
       gesturesEnabled = true,
       drawerState = viewModel.drawerState,
       scrimColor = MaterialTheme.colorScheme.secondary,
    ) {
       Navigation(navHostController, viewModel)
   }
}