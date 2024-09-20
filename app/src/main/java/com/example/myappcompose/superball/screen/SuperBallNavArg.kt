package com.example.myappcompose.superball.screen

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class SuperBallNavArg(
    val route: String, val navArguments: List<NamedNavArgument> = emptyList()
) {

    object SelectionDetail : SuperBallNavArg(
        route = "game?model={model}&isUpdate={isUpdate}",
        listOf(navArgument("model") {
            type = NavType.StringType
        }, navArgument("isUpdate") {
            type = NavType.BoolType
        })
    ) {

    }

    object GameList : SuperBallNavArg(
        route = "model/{model}",
        listOf(navArgument("model") {
            type = NavType.StringType
        })
    ) {
        fun createRouteToSelection(model: String, isUpdate: Boolean) =
            "game?model=${model}&isUpdate=${isUpdate}"

    }

    object SuperBallHome : SuperBallNavArg(
        route = "home"
    ) {
        fun createRouteToGame(model: String) = "model/${model}"

    }

    object Resultant : SuperBallNavArg(
        route = "resultant"
    ) {
    }


}