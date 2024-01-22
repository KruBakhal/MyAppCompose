package com.example.myappcompose.utils

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String, val navArguments: List<NamedNavArgument> = emptyList()
) {

    object ProductDetail : Screen(
        route = "productdetail/{detail}",
        navArguments = listOf(navArgument("detail") {
            type = NavType.StringType
        })
    ) {

    }

    object ProductList : Screen(
        route = "productlist/{category}",
        listOf(navArgument("category") {
            type = NavType.StringType
        })
    ) {
        fun createRouteToDetail(detail: String) = "productdetail/${detail}"

    }


    object Home : Screen(
        route = "home"
    ) {
        fun createRouteToProduct(category: String) = "productlist/${category}"

    }


}