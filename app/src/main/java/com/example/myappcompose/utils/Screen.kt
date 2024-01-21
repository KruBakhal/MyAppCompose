package com.example.myappcompose.utils

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
     object Home : Screen("home")

     object PlantDetail : Screen(
        route = "plantDetail/{plantId}",
        navArguments = listOf(navArgument("plantId") {
            type = NavType.StringType
        })
    ) {
        fun createRoute(plantId: String) = "plantDetail/${plantId}"
    }

    object Gallery : Screen(
        route = "gallery/{plantName}",
        navArguments = listOf(navArgument("plantName") {
            type = NavType.StringType
        })
    ) {
        fun createRoute(plantName: String) = "gallery/${plantName}"

    }
}