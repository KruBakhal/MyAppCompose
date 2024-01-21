package com.example.myappcompose.compose

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ShareCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myappcompose.R
import com.example.myappcompose.utils.Screen

@Composable
fun ShopApp() {
    val navController = rememberNavController()
    SunFlowerNavHost(
        navController = navController
    )
}

@Composable
fun SunFlowerNavHost(
    navController: NavHostController
) {
    val activity = (LocalContext.current as Activity)
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                onClick = {
                    /*navController.navigate(
                         Screen.PlantDetail.createRoute(
                             plantId = it.plantId
                         )
                    )*/
                }
            )
        }
        composable(
            route = Screen.Gallery.route,
            arguments = Screen.Gallery.navArguments
        ) {
            ProductScreen(
                onClick = {

                }
            )
        }
        composable(
            route = Screen.PlantDetail.route,
            arguments = Screen.PlantDetail.navArguments
        ) {
            DetailScreen(
                /* onBackClick = { navController.navigateUp() },
                 onShareClick = {
                     createShareIntent(activity, it)
                 },
                 onGalleryClick = {
                     navController.navigate(
                         Screen.Gallery.createRoute(
                             plantName = it.name
                         )
                     )
                 }*/
            )
        }

    }
}

// Helper function for calling a share functionality.
// Should be used when user presses a share button/menu item.
/*
private fun createShareIntent(activity: Activity, plantName: String) {
    val shareText = activity.getString(R.string.share_text_plant, plantName)
    val shareIntent = ShareCompat.IntentBuilder(activity)
        .setText(shareText)
        .setType("text/plain")
        .createChooserIntent()
        .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
    activity.startActivity(shareIntent)
}*/
