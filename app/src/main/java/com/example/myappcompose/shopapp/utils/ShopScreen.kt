package com.example.myappcompose.shopapp.utils

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class ShopScreen(
    val route: String, val navArguments: List<NamedNavArgument> = emptyList()
) {

    object ProductDetail : ShopScreen(
        route = "productdetail/{detail}",
        navArguments = listOf(navArgument("detail") {
            type = NavType.StringType
        })
    ) {

    }

    object ProductList : ShopScreen(
        route = "productlist/{category}",
        listOf(navArgument("category") {
            type = NavType.StringType
        })
    ) {
        fun createRouteToDetail(detail: String) = "productdetail/${detail}"

    }


    object Home : ShopScreen(
        route = "home"
    ) {
        fun createRouteToProduct(category: String) = "productlist/${category}"

    }


}