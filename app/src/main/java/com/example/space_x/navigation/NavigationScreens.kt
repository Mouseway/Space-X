package com.example.space_x.navigation

// List of screens
sealed class NavigationScreens(val route: String) {
    object CompanyScreen : NavigationScreens("company_screen")
    object RocketsScreen : NavigationScreens("rockets_screen")
    object RocketDetailScreen : NavigationScreens("rocket_detail_screen")
    object LaunchesScreen : NavigationScreens("launches_screen")
    object SettingsScreen : NavigationScreens("settings_screen")
}
