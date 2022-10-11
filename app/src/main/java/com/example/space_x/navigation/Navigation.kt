package com.example.space_x.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.space_x.screens.SharedViewModel
import com.example.space_x.screens.company.CompanyScreen
import com.example.space_x.screens.launches.LaunchesScreen
import com.example.space_x.screens.rocketDetail.RocketDetailScreen
import com.example.space_x.screens.rockets.RocketsScreen
import com.example.space_x.screens.settings.SettingsScreen

@Composable
fun Navigation(navHostController: NavHostController, sharedViewModel: SharedViewModel) {
    NavHost(navController = navHostController,
        startDestination = NavigationScreens.CompanyScreen.route,
        ) {
        // About company screen
        composable( route = NavigationScreens.CompanyScreen.route){
            CompanyScreen(sharedViewModel)
        }
        // Rockets screen
        composable( route = NavigationScreens.RocketsScreen.route){
            RocketsScreen(navHostController, sharedViewModel)
        }
        // Rocket detail screen
        // Takes the rocket id as parameter
        composable(
            route = NavigationScreens.RocketDetailScreen.route + "/{rocketId}",
            arguments = listOf(navArgument(name = "rocketId"){
                type = NavType.StringType
            })
        ) { entry ->
            RocketDetailScreen(rocketId = entry.arguments?.getString("rocketId"), sharedViewModel)
        }
        // Setting screen
        composable(route = NavigationScreens.SettingsScreen.route){
            SettingsScreen(sharedViewModel)
        }
        // Launches screen
        composable(route = NavigationScreens.LaunchesScreen.route){
            LaunchesScreen(sharedViewModel)
        }
    }
}