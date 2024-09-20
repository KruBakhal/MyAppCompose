package com.example.myappcompose.superball.screen

import android.app.Activity
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myappcompose.ui.theme.MyAppComposeTheme
import com.example.practicesession.superballgame.model.SuperBallModel
import com.google.gson.Gson

@Composable
fun SuperBallApp() {
    MyAppComposeTheme {
        val navController = rememberNavController()
        SuperBallNavHost(
            navController = navController
        )
    }
}

@Composable
fun SuperBallNavHost(
    navController: NavHostController
) {
    val activity = (LocalContext.current as Activity)
    NavHost(navController = navController, startDestination = SuperBallNavArg.SuperBallHome.route) {
        composable(
            route = SuperBallNavArg.SuperBallHome.route,
            arguments = SuperBallNavArg.SuperBallHome.navArguments
        ) {
            SuperBallHome(function = {
                navController.navigate(
                    SuperBallNavArg.SuperBallHome.createRouteToGame(
                        model = Uri.encode(Gson().toJson(it))
                    )
                )
            }) {
                navController.navigate(
                    SuperBallNavArg.Resultant.route
                )
            }
        }
        composable(
            route = SuperBallNavArg.Resultant.route,
            arguments = SuperBallNavArg.Resultant.navArguments
        ) {
            ResultantScreenFunc {
                navController.navigateUp()
            }
        }
        composable(
            route = SuperBallNavArg.GameList.route,
            arguments = SuperBallNavArg.GameList.navArguments
        ) {
            val json = it.arguments?.getString("model")
            val model = if (!json.isNullOrEmpty()) {
                Gson().fromJson(json, SuperBallModel::class.java)
            } else {
                null
            }

            GamesListScreen(model, onBackClick = {
                navController.navigateUp()
            }, onMoveToSelection = { it, isUpdate ->
                navController.navigate(
                    SuperBallNavArg.GameList.createRouteToSelection(
                        model = Uri.encode(Gson().toJson(it)), isUpdate
                    )
                )
            })
        }
        composable(
            route = SuperBallNavArg.SelectionDetail.route,
            arguments = SuperBallNavArg.SelectionDetail.navArguments
        ) {
            val json = it.arguments?.getString("model")
            val isUpdate = it.arguments?.getBoolean("isUpdate", false)
            val product = if (!json.isNullOrEmpty()) {
                Gson().fromJson(json, SuperBallModel::class.java)
            } else {
                null
            }
            SelectionScreenFunc(product, isUpdate,
                onBackClick = {
                    navController.navigateUp()
                }
            ) {

            }
        }
        composable(
            route = SuperBallNavArg.SelectionDetail.route,
            arguments = SuperBallNavArg.SelectionDetail.navArguments
        ) {
            val json = it.arguments?.getString("model")
            val isUpdate = it.arguments?.getBoolean("isUpdate", false)
            val product = if (!json.isNullOrEmpty()) {
                Gson().fromJson(json, SuperBallModel::class.java)
            } else {
                null
            }
            SelectionScreenFunc(product, isUpdate,
                onBackClick = { navController.navigateUp() }
            ) {

            }
        }

    }
}

